package com.senla.library.ui;

import java.util.List;

import com.senla.library.bean.IEntity;

public interface IResponse {
	public List<? extends IEntity> getList();
	public boolean getEnd();
}
