package com.senla.library.transmission;

import java.io.Serializable;
import java.util.List;

import com.senla.library.api.bean.IEntity;
import com.senla.library.api.transmitter.response.IResponse;

public class Response implements IResponse, Serializable {
	private static final long serialVersionUID = 8175908351946302643L;
	private boolean returnOption;
	private boolean isExit;
	private List<? extends IEntity> entities;
	private String message;

	public Response(String message) {
		this.message = message;		
		returnOption = true;
	}		
	
	@Override
	public void completeMessage(String annex) {
		message += annex;
	}	
	
	@Override
	public boolean isExit() {
		return isExit;
	}
	
	@Override
	public void setExit(boolean isExit) {
		returnOption = !isExit;
		this.isExit = isExit;
	}
	
	@Override
	public boolean hasReturnOption() {
		return returnOption;
	}
	
	@Override
	public void setReturnOption(boolean returnOption) {
		this.returnOption = returnOption;
	}

	@Override
	public List<? extends IEntity> getEntities() {
		return entities;
	}	
	
	@Override
	public void setEntities(List<? extends IEntity> entities) {		
		this.entities = entities;
	}	
	
	@Override
	public String toString() {
		return message.toString();
	}	
}
