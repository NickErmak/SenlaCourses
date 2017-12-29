package com.senla.library.api.transmitter.response;

import java.util.List;

import com.senla.library.api.bean.IEntity;

public interface IResponse {
	
	public List<? extends IEntity> getEntities();
	public void setEntities(List<? extends IEntity> entities);	
	public void completeMessage(String annex);	
	public boolean isExit(); 
	public void setExit(boolean isExit); 
	public boolean hasReturnOption();
	public void setReturnOption(boolean returnOption);	
}
