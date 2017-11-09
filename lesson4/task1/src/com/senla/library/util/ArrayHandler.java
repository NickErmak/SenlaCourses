package com.senla.library.util;

import com.senla.library.entity.Entity;

public class ArrayHandler {

	public static int getFreeCellIndex(Entity[] array) {
		for (int i = 0; i < array.length; i++)
			if (array[i] == null)
				return i;
		return -1;
	}		
	
	public static Entity getElementById(int id, Entity[] array) {
		for (int i = 0; i < array.length; i++) 
			if (array[i] != null && id == array[i].getId())
				return array[i];		
		return null;
	}

	public static int getElementQuantity(Entity[] array) {
		int quantity = 0;
		for (Entity entity : array)
			if (entity != null)
				++quantity;
		return quantity;
	}

	public static String[] getStringArray(Entity[] array) {
		String[] stringEntity = new String[getElementQuantity(array)];
		for (int i = 0; i < array.length; i++)
			if (array[i] != null)
				stringEntity[i] = array[i].toString();
		return stringEntity;
	}
}
