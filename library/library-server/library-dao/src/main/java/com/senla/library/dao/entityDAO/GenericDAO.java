package com.senla.library.dao.entityDAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.senla.library.api.bean.IEntity;
import com.senla.library.api.dao.IGenericDAO;
import com.senla.library.api.dao.SortingCriteria;
import com.senla.library.dao.EntityInfo;
import com.senla.library.dao.EntityManager;
import com.senla.library.dao.connection.ConnectionHolder;
import com.senla.library.dao.util.QueryGenerator;

public abstract class GenericDAO<T extends IEntity> implements IGenericDAO<T> {
	private static Logger logger = Logger.getLogger(GenericDAO.class);
	private final ConnectionHolder connectionHolder;
	private EntityManager entityManager;

	public GenericDAO() throws Exception {
		entityManager = new EntityManager();
		connectionHolder = ConnectionHolder.getinstance();
	}

	@Override
	public void update(T entity) throws Exception {
		EntityInfo entityInfo = entityManager.getEntityInfo(entity.getClass());
		executeUpdate(QueryGenerator.getUpdateQuery(entityInfo, entity));
	}

	@Override
	public void add(T entity) throws Exception {
		EntityInfo entityInfo = entityManager.getEntityInfo(entity.getClass());
		executeUpdate(QueryGenerator.getInsertQuery(entityInfo, entity));
	}

	@Override
	public List<T> getAll(Class<? extends T> clazz) throws Exception {
		return getAll(clazz, null);
	}

	@Override
	public List<T> getAll(Class<? extends T> clazz, SortingCriteria sortingCriteria) throws Exception {
		return getAll(clazz, sortingCriteria, null, null);
	}

	@Override
	public List<T> getAll(Class<? extends T> clazz, SortingCriteria sortingCriteria, Field date, Date dateFrom) throws Exception {
		return getAll(clazz, sortingCriteria, date, dateFrom, null);
	}

	@Override
	public List<T> getAll(Class<? extends T> clazz, SortingCriteria sortingCriteria, Field date, Date dateFrom,
			Date dateTo) throws Exception {
		EntityInfo entityInfo = entityManager.getEntityInfo(clazz);
		String query = QueryGenerator.getSelectAllQuery(entityInfo, sortingCriteria, date, dateFrom, dateTo);
		ResultSet resultSet = executeQuery(query);
		return createEntities(entityInfo, resultSet);
	}

	@Override
	public T get(Class<? extends T> clazz, int id) throws Exception {
		EntityInfo entityInfo = entityManager.getEntityInfo(clazz);
		String query = QueryGenerator.getSelectByPKQuery(entityInfo, id);
		ResultSet resultSet = executeQuery(query);
		return createEntities(entityInfo, resultSet).get(0);
	}

	private ResultSet executeQuery(String query) throws Exception {
		ResultSet resultSet = null;
		try (PreparedStatement preStatement = connectionHolder.getConnection().prepareStatement(query)) {			
			resultSet = preStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e);	
			throw new Exception(e);
		}
		return resultSet;
	}

	private void executeUpdate(String query) throws Exception {
		try {
			connectionHolder.getConnection().prepareStatement(query).executeUpdate();
		} catch (SQLException e) {
			logger.error(e);	
			throw new Exception(e);
		}
	}

	private String getSetMethod(String column) {
		StringBuilder setMethod = new StringBuilder().append("set");
		for (String s : column.split("_")) {
			setMethod.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
		}
		return setMethod.toString();
	}

	private T createEntity(ResultSet resultSet, EntityInfo entityInfo, T newEntity)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException,
			NoSuchMethodException, SecurityException {
		
			Iterator<String> columnsIterator = entityInfo.getColumns().iterator();
			while (columnsIterator.hasNext()) {
				String columnName = columnsIterator.next();
				Method setMethod = newEntity.getClass().getMethod(getSetMethod(columnName), String.class);
				setMethod.invoke(newEntity, resultSet.getString(entityInfo.getTableName() + "." + columnName));
			}		
		return newEntity;
	}

	@SuppressWarnings("unchecked")
	private List<T> createEntities(EntityInfo entityInfo, ResultSet resultSet) throws Exception {
		Map<String, T> entities = new HashMap<>();
		Class<T> clazz = (Class<T>) entityInfo.getClazz();
		try {
			while (resultSet.next()) {
				String uniqueKey = entityInfo.getTableName() + resultSet.getString(entityInfo.getPk());
				T newEntity;
				if (entities.containsKey(uniqueKey)) {
					newEntity = entities.get(uniqueKey);
				} else {
					newEntity = createEntity(resultSet, entityInfo, clazz.newInstance());
					Iterator<EntityInfo> relationsManyToOne = entityManager.getJoinColumnsManyToOne(clazz).iterator();
					while (relationsManyToOne.hasNext()) {
						EntityInfo relationEntityInfo = relationsManyToOne.next();
						Class<T> clazzRelation = (Class<T>) relationEntityInfo.getClazz();
						T newRelation = clazzRelation.newInstance();
						newRelation = createEntity(resultSet, relationEntityInfo, newRelation);
						Method setMethod = clazz.getDeclaredMethod(getSetMethod(relationEntityInfo.getTableName()),
								clazzRelation);
						setMethod.invoke(newEntity, newRelation);
					}
				}
				Iterator<EntityInfo> relationsOneToMany = entityManager.getJoinColumnsOneToMany(clazz).iterator();
				while (relationsOneToMany.hasNext()) {
					EntityInfo relationEntityInfo = relationsOneToMany.next();
					Class<T> relationClazz = (Class<T>) relationEntityInfo.getClazz();
					Method methodAddRelation = clazz.getMethod("add" + relationClazz.getSimpleName(), relationClazz);
					T newRelation = relationClazz.newInstance();
					newRelation = createEntity(resultSet, relationEntityInfo, newRelation);
					methodAddRelation.invoke(newEntity, newRelation);
				}
				entities.put(uniqueKey, newEntity);
			}
		} catch (InstantiationException | IllegalAccessException | SecurityException | InvocationTargetException
				| NoSuchMethodException | IllegalArgumentException | SQLException e) {
			logger.error(e);	
			throw new Exception(e);
		}
		return new ArrayList<T>(entities.values());
	}
	
	public Connection getConnection() throws SQLException {
		return connectionHolder.getConnection();
	}

	public void exit() throws Exception {
		try {
			connectionHolder.closeConnection();
		} catch (SQLException e) {			
			logger.error(e);
			throw new Exception(e);
		}
	}
}
