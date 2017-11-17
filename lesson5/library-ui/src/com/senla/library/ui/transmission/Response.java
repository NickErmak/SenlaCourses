package com.senla.library.ui.transmission;

import java.util.List;

import com.senla.library.bean.IEntity;
import com.senla.library.ui.IResponse;

public class Response implements IResponse {

	private boolean isEnd;
	private List<? extends IEntity> list;
	private ResponseMessage message;

	public Response(ResponseMessage message) {
		this.message = message;
		if (message == ResponseMessage.EXIT)
			isEnd = true;
		else
			isEnd = false;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public boolean getEnd() {
		return isEnd;
	}

	@Override
	public List<? extends IEntity> getList() {
		return list;
	}

	@Override
	public String toString() {
		return message.toString();
	}

}
