package com.senla.library.dao.entityDAO;

import static com.senla.library.dao.connection.ConnectionHolder.getConnection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.library.api.annotation.di.Inject;
import com.senla.library.api.bean.IEntity;
import com.senla.library.api.dao.IGenericDAO;
import com.senla.library.api.dao.SortingCriteria;
import com.senla.library.dao.EntityInfo;
import com.senla.library.dao.EntityManager;
import com.senla.library.dao.connection.ConnectionHolder;
import com.senla.library.dao.util.QueryGenerator;

public abstract class GenericDAO implements IGenericDAO {
	private static Logger logger = Logger.getLogger(GenericDAO.class);
	@Inject
	private EntityManager entityManager;	

	public GenericDAO() {
		entityManager = new EntityManager();
	}

	@Override
	public <T extends IEntity> void update(T entity) {
		EntityInfo entityInfo = entityManager.getEntityInfo(entity.getClass());
		executeUpdate(QueryGenerator.getUpdateQuery(entityInfo, entity));
	}

	@Override
	public <T extends IEntity> void add(T entity) {
		EntityInfo entityInfo = entityManager.getEntityInfo(entity.getClass());
		executeUpdate(QueryGenerator.getInsertQuery(entityInfo, entity));
	}

	@Override
	public <T extends IEntity> List<T> getAll(Class<T> clazz, SortingCriteria sortingCriteria) {
		EntityInfo entityInfo = entityManager.getEntityInfo(clazz);
		String query = QueryGenerator.getSelectAllQuery(entityInfo, sortingCriteria);
		ResultSet resultSet = executeQuery(query);
		return createEntities(entityInfo, resultSet);
	}

	@Override
	public <T extends IEntity> List<T> getAll(Class<T> clazz) {
		return getAll(clazz, SortingCriteria.ID);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IEntity> T get(Class<T> clazz, int id) {
		EntityInfo entityInfo = entityManager.getEntityInfo(clazz);
		String query = QueryGenerator.getSelectByPKQuery(entityInfo, id);
		ResultSet resultSet = executeQuery(query);
		return (T) createEntities(entityInfo, resultSet).get(0);
	}

	private ResultSet executeQuery(String query) {
		ResultSet resultSet = null;
		try {
			PreparedStatement preStatement = getConnection().prepareStatement(query);
			resultSet = preStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e);
		}
		return resultSet;
	}

	private void executeUpdate(String query) {
		System.out.println(query);
		try {
			getConnection().prepareStatement(query).executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	private String getSetMethod(String column) {
		StringBuilder setMethod = new StringBuilder().append("set");
		for (String s : column.split("_")) {
			setMethod.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
		}
		return setMethod.toString();
	}

	private <T extends IEntity> T createEntity(ResultSet resultSet, EntityInfo entityInfo, T newEntity) {
		try {
			Iterator<String> columnsIterator = entityInfo.getColumns().iterator();
			while (columnsIterator.hasNext()) {
				String columnName = columnsIterator.next();
				Method setMethod = newEntity.getClass().getMethod(getSetMethod(columnName), String.class);
				setMethod.invoke(newEntity, resultSet.getString(columnName));
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException
				| NoSuchMethodException | SecurityException e) {
			logger.error(e);
		}
		return newEntity;
	}

	@SuppressWarnings("unchecked")
	private <T extends IEntity> List<T> createEntities(EntityInfo entityInfo, ResultSet resultSet) {
		List<T> entities = new ArrayList<>();
		Class<T> clazz = (Class<T>) entityInfo.getClazz();
		try {
			while (resultSet.next()) {
				T newEntity = clazz.newInstance();
				newEntity = createEntity(resultSet, entityInfo, newEntity);

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

				Iterator<EntityInfo> relationsOneToMany = entityManager.getJoinColumnsOneToMany(clazz).iterator();
				while (relationsOneToMany.hasNext()) {
					EntityInfo relationEntityInfo = relationsOneToMany.next();
					Class<T> relationClazz = (Class<T>) relationEntityInfo.getClazz();
					Method methodAddRelation = clazz.getMethod("add" + relationClazz.getSimpleName(), relationClazz);
					T newRelation = relationClazz.newInstance();
					newRelation = createEntity(resultSet, relationEntityInfo, newRelation);
					methodAddRelation.invoke(newEntity, newRelation);
				}
				entities.add(newEntity);
			}
		} catch (InstantiationException | IllegalAccessException | SecurityException | InvocationTargetException
				| NoSuchMethodException | IllegalArgumentException | SQLException e) {
			logger.error(e);
		}
		return entities;
	}

	public void close() {
		ConnectionHolder.closeConnection();
	}
}
