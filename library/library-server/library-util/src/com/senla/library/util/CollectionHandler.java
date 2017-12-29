package com.senla.library.util;

import java.util.Iterator;
import java.util.List;

import com.senla.library.api.bean.IEntity;
import com.senla.library.api.exception.NoSuchIdException;

public class CollectionHandler {

	public static <T extends IEntity> T getElementById(Integer id, List<T> entities) throws NoSuchIdException {
		Iterator<T> iterator = entities.iterator();
		while (iterator.hasNext()) {
			T entity = iterator.next();
			if (id == entity.getId()) {
				return entity;
			}
		}
		throw new NoSuchIdException(id);
	}	
}
