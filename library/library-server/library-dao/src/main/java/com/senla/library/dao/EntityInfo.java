package com.senla.library.dao;

import java.util.ArrayList;
import java.util.List;

import com.senla.library.api.bean.IEntity;

public class EntityInfo {
	private Class<? extends IEntity> clazz;
	private String tableName;
	private String pk;	
	private String mappedBy;
	private String oppositeMappedBy;
	private List<String> columns;
	private List<EntityInfo> relationsManyToOne;
	private List<EntityInfo> relationsBindingTable;
	
	
	public EntityInfo(String tableName, Class<? extends IEntity> clazz) {
		relationsManyToOne = new ArrayList<>();
		relationsBindingTable = new ArrayList<>();
		this.clazz = clazz;
		this.tableName = tableName;
	}		

	public Class<? extends IEntity> getClazz(){
		return clazz;
	}
	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public EntityInfo addRelationManyToOne(EntityInfo entityInfo) {
		relationsManyToOne.add(entityInfo);
		return this;
	}
	
	public List<EntityInfo> getRelationsManyToOne() {
		return relationsManyToOne;
	}
	
	public EntityInfo addRelationBindingTable(EntityInfo entityInfo) {
		relationsBindingTable.add(entityInfo);
		return this;
	}
	
	public List<EntityInfo> getRelationsBindingTable() {
		return relationsBindingTable;
	}
	
	public String getMappedBy() {
		return mappedBy;
	}
	
	public void setMappedBy(String mappeBy) {
		this.mappedBy = mappeBy;
	}
	
	public String getOppositeMappedBy() {
		return oppositeMappedBy;
	}
	
	public void setOppositeMappedBy(String oppositeMappeBy) {
		this.oppositeMappedBy = oppositeMappeBy;
	}
	
}
