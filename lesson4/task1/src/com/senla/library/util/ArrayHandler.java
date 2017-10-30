package com.senla.library.util;

import com.senla.library.entity.EntityId;

public class ArrayHandler {

	public static int getFreeCellIndex(EntityId[] array) {
		for (int i = 0; i < array.length; i++)
			if (array[i] == null)
				return i;
		return -1;
	}

	public static int getElementIndex(int id, EntityId[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null)
				if (id == array[i].getId())
					return i;
		}
		return -1;
	}

	public static int getElementQuantity(EntityId[] array) {
		int quantity = 0;
		for (EntityId entity : array)
			if (entity != null)
				++quantity;
		return quantity;
	}
}
