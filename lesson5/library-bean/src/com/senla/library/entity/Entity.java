package com.senla.library.entity;

import java.io.Serializable;

import com.senla.library.api.bean.IEntity;

public abstract class Entity implements IEntity, Serializable{
	
	private static final long serialVersionUID = 758415839269489923L;

	@Override
	public abstract int getId();

}
