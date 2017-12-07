package com.senla.library.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.senla.library.api.bean.IEntity;
import com.senla.library.api.exception.NoSuchIdException;

public class CollectionHandler {

	public static <T extends IEntity> T getElementById(Integer id, List<T> array) throws NoSuchIdException {
		for (int i = 0; i < array.size(); i++) {
			if (id == array.get(i).getId()) {
				return array.get(i);
			}
		}
		throw new NoSuchIdException(id);
	}

	public static List<String[]> parseToStringWithHeader(List<? extends IEntity> entityList, String[] header) {
		List<String[]> stringList = new ArrayList<>();
		stringList.add(header);
		Iterator<? extends IEntity> it = entityList.iterator();
		while (it.hasNext()) {
			IEntity entity = it.next();
			stringList.add(entity.toStringCSV());
		}
		return stringList;
	}
}
