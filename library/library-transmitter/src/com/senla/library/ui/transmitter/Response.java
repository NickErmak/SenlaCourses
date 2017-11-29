package com.senla.library.ui.transmitter;

import java.util.List;

import com.senla.library.api.bean.IEntity;
import com.senla.library.api.transmitter.response.IResponse;

public class Response implements IResponse {

	private boolean exit;
	private List<? extends IEntity> entities;
	private String message;

	public Response(String message) {
		this.message = message;		
	}		
	
	@Override
	public boolean isExit() {
		return exit;
	}
	
	@Override
	public void setExit(boolean exit) {
		this.exit = exit;
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
	public void completeMessage(String annex) {
		message += annex;
	}
	
	@Override
	public String toString() {
		return message.toString();
	}	

}
