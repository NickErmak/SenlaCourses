package com.senla.library.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.library.api.annotation.dao.BindingTable;
import com.senla.library.api.annotation.dao.ColumnDAO;
import com.senla.library.api.annotation.dao.EntityDAO;
import com.senla.library.api.annotation.dao.ManyToOne;
import com.senla.library.api.bean.IEntity;

public class EntityManager {

	private Map<String, EntityInfo> entityInfo;
	
	public EntityManager() {
		entityInfo = new HashMap<>();
	}
	
	public <T extends IEntity> EntityInfo getEntityInfo(Class<T> clazz) {
		return analiseEntity(clazz);
	}
	
	public <T extends IEntity> List<String> getColumns(Class<T> clazz) {
		return analiseEntity(clazz).getColumns();
	}
	
	public <T extends IEntity> List<EntityInfo> getJoinColumnsManyToOne(Class<T> clazz) {
		return analiseEntity(clazz).getRelationsManyToOne();
	}
	
	public <T extends IEntity> List<EntityInfo> getJoinColumnsOneToMany(Class<T> clazz) {
		return analiseEntity(clazz).getRelationsBindingTable();
	}	

	@SuppressWarnings("unchecked")
	private <T extends IEntity> EntityInfo analiseEntity(Class<T> clazz) {
		EntityDAO aEntityDAO = clazz.getAnnotation(EntityDAO.class);
		String table = aEntityDAO.table();
		if (entityInfo.containsKey(table))
			return entityInfo.get(table);
		else {
			EntityInfo newEntityInfo = new EntityInfo(table, clazz);
			newEntityInfo.setPk(aEntityDAO.pk());
			List<String> columns = new ArrayList<>();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(ColumnDAO.class)) {
					columns.add(field.getAnnotation(ColumnDAO.class).name());
				}
				if (field.isAnnotationPresent(ManyToOne.class)) {
					ManyToOne aManyToOne = field.getAnnotation(ManyToOne.class);
					
					EntityInfo innerEntityInfo = analiseEntity((Class<T>) field.getType());
					innerEntityInfo.setMappedBy(aManyToOne.mappedBy());
					newEntityInfo.addRelationManyToOne(innerEntityInfo);
				}
				if (field.isAnnotationPresent(BindingTable.class)) {
					BindingTable aBindingTable = field.getAnnotation(BindingTable.class);
					Type type = field.getGenericType();
					ParameterizedType parameterizedType = (ParameterizedType) type;					
					Class<T> clazzRelation = (Class<T>) parameterizedType.getActualTypeArguments()[0];	
					EntityInfo innerEntityInfo = analiseEntity(clazzRelation);
					innerEntityInfo.setMappedBy(aBindingTable.mappedBy());
					innerEntityInfo.setOppositeMappedBy(aBindingTable.oppositeMappedBy());
					newEntityInfo.addRelationBindingTable(innerEntityInfo);
				}				
			}
			newEntityInfo.setColumns(columns);
			entityInfo.put(table, newEntityInfo);
			return newEntityInfo;
		}
	}
}
