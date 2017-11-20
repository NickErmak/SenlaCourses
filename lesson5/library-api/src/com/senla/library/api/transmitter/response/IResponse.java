package com.senla.library.api.transmitter.response;

import java.util.List;

import com.senla.library.api.bean.IEntity;

public interface IResponse {
	public List<? extends IEntity> getEntities();
	public void setEntities(List<? extends IEntity> entities);
	public boolean isExit();
	public void setExit(boolean exit);	
	public void completeMessage(String annex);
}
