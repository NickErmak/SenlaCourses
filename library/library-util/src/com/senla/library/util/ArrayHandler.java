package com.senla.library.util;

import java.util.List;

import com.senla.library.api.bean.IEntity;
import com.senla.library.api.exception.NoSuchIdException;

public class ArrayHandler {

	public static <T extends IEntity> T getElementById(int id, List<T> array) throws NoSuchIdException {
		for (int i = 0; i < array.size(); i++)
			if (id == array.get(i).getId())
				return array.get(i);
		;		
		throw new NoSuchIdException();
	}

	public static String[] getStringArray(List<? extends IEntity> list) {
		String[] array = new String[list.size()];
		for (int i = 0; i < list.size(); i++)
			array[i] = list.get(i).toString();
		return array;
	}

}
