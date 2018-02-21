package com.senla.library.dao.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.library.api.annotation.dao.ColumnDAO;
import com.senla.library.api.bean.IEntity;
import com.senla.library.api.dao.SortingCriteria;
import com.senla.library.dao.EntityInfo;
import com.senla.library.util.DateConverter;

public class QueryGenerator {
	private static Logger logger = Logger.getLogger(QueryGenerator.class);
	private static final String INSERT_INTO = "INSERT INTO ";
	private static final String SELECT_FROM = "SELECT * FROM ";
	private static final String VALUES = " VALUES ";
	private static final String ORDER_BY = " ORDER BY ";
	private static final String WHERE = " WHERE ";
	private static final String UPDATE = "UPDATE ";
	private static final String SET = " SET ";
	private static final String BETWEEN = " BETWEEN ";
	private static final String AND = " AND ";

	public static <T extends IEntity> String getUpdateQuery(EntityInfo entityInfo, T entity) throws Exception {
		StringBuilder query = new StringBuilder().append(UPDATE);
		query.append(entityInfo.getTableName()).append(SET);
		Iterator<String> columnsIterator = entityInfo.getColumns().iterator();
		try {
			while (columnsIterator.hasNext()) {
				String column = columnsIterator.next();
				query.append(column).append(" = ");
				Method getMethod = entity.getClass().getDeclaredMethod(getMethodGet(column));
				query.append(getMethod.invoke(entity)).append(", ");
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.error(e);
			throw new Exception(e);
		}
		query.setLength(query.length() - 2);
		query.append(WHERE).append(entityInfo.getPk()).append(" = ").append(entity.getId());
		return query.toString();
	}

	public static <T extends IEntity> String getInsertQuery(EntityInfo entityInfo, T entity) throws Exception {
		List<String> columns = entityInfo.getColumns();
		StringBuilder query = new StringBuilder().append(INSERT_INTO);
		query.append(entityInfo.getTableName());
		query.append(getInsertColumns(columns));
		query.append(VALUES);
		query.append(getColumnValues(entity, columns));
		return query.toString();
	}

	public static String getSelectByPKQuery(EntityInfo entityInfo, int id) {
		StringBuilder query = new StringBuilder().append(SELECT_FROM);
		query.append(getTable(entityInfo)).append(WHERE);
		query.append(entityInfo.getTableName()).append(".");
		query.append(entityInfo.getPk()).append(" = ").append(id);
		return query.toString();
	}

	public static <T extends IEntity> String getSelectAllQuery(EntityInfo entityInfo, SortingCriteria sortingCriteria,
			Field date, Date dateFrom, Date dateTo) {
		StringBuilder query = new StringBuilder().append(SELECT_FROM);
		query.append(getTable(entityInfo)).toString();
		if (date != null && (dateFrom != null || dateTo != null)) {
			query.append(WHERE).append(entityInfo.getTableName()).append(".");
			query.append(date.getAnnotation(ColumnDAO.class).name());
			if (dateFrom != null && dateTo == null) {
				query.append("<");
				query.append("'").append(DateConverter.dateToString(dateFrom)).append("'");
			}
			if (dateFrom != null && dateTo != null) {
				query.append(BETWEEN).append("'").append(DateConverter.dateToString(dateFrom)).append("'");
				query.append(AND).append("'").append(DateConverter.dateToString(dateTo)).append("'");
			}
		}
		if (sortingCriteria != null) {
			query.append(ORDER_BY).append(sortingCriteria.toString());
		}
		return query.toString();
	}

	private static <T extends IEntity> String getColumnValues(T entity, List<String> columns) throws Exception {
		StringBuilder columnsValue = new StringBuilder("(");
		Iterator<String> columnsIterator = columns.iterator();
		try {
			while (columnsIterator.hasNext()) {
				Method getMethod = entity.getClass().getDeclaredMethod(getMethodGet(columnsIterator.next()));
				columnsValue.append("'").append(getMethod.invoke(entity).toString()).append("', ");
			}
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException | InvocationTargetException
				| NoSuchMethodException e) {
			logger.error(e);
			throw new Exception(e);
		}
		columnsValue.setLength(columnsValue.length() - 2);
		return columnsValue.append(")").toString();
	}

	private static String getInsertColumns(List<String> columns) {
		StringBuilder columnsBuilder = new StringBuilder(" (");
		Iterator<String> columnsIterator = columns.iterator();
		while (columnsIterator.hasNext()) {
			columnsBuilder.append(columnsIterator.next()).append(", ");
		}
		columnsBuilder.setLength(columnsBuilder.length() - 2);
		columnsBuilder.append(")");
		return columnsBuilder.toString();
	}

	public static <T extends IEntity> String getTable(EntityInfo entityInfo) {
		String mainTable = entityInfo.getTableName();
		String query = new String(mainTable);
		List<EntityInfo> relationsManyToOne = entityInfo.getRelationsManyToOne();
		if (!relationsManyToOne.isEmpty()) {
			Iterator<EntityInfo> iterator = relationsManyToOne.iterator();
			while (iterator.hasNext()) {
				EntityInfo relation = iterator.next();
				String join = " join " + relation.getTableName() + " on " + relation.getMappedBy() + " = "
						+ relation.getTableName() + "." + relation.getPk();
				query += join;
			}
		}
		List<EntityInfo> relationsOneToMany = entityInfo.getRelationsBindingTable();
		if (!relationsOneToMany.isEmpty()) {
			Iterator<EntityInfo> iterator = relationsOneToMany.iterator();
			while (iterator.hasNext()) {
				EntityInfo relation = iterator.next();
				String subTable = mainTable + "_" + relation.getTableName();
				String join = " join " + subTable + " on " + subTable + "." + relation.getMappedBy() + " = " + mainTable
						+ "." + entityInfo.getPk();
				String join2 = " join " + relation.getTableName() + " on " + subTable + "."
						+ relation.getOppositeMappedBy() + " = " + relation.getTableName() + "." + relation.getPk();
				query += join;
				query += join2;
			}
		}
		return query;
	}

	private static String getMethodGet(String column) {
		String getMethodName = "get" + column.substring(0, 1).toUpperCase() + column.substring(1);
		return getMethodName;
	}
}
