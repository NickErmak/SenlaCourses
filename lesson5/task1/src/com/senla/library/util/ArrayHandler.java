package com.senla.library.util;

import java.util.List;

import com.senla.library.entity.Entity;

public class ArrayHandler {

	public static <T extends Entity> T getElementById(int id, List<T> array) {
		for (int i = 0; i < array.size(); i++)
			if (id == array.get(i).getId())
				return array.get(i);
		return null;
	}

	public static String[] getStringArray(List<? extends Entity> list) {
		String[] array = new String[list.size()];
		for (int i = 0; i < list.size(); i++)
			array[i] = list.get(i).toString();
		return array;
	}

}
