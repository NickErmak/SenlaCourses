
public class ArrayHandler {

	public static int getFreeCellIndex(IdElement[] array) {
		for (int i = 0; i < array.length; i++)
			if (array[i] == null)
				return i;
		return -1;
	}

	public static int getElementIndex(int id, IdElement[] array) {

		for (int i = 0; i < array.length; i++) {
			if (array[i] != null)
				if (id == array[i].getId())
					return i;
		}
		return -1;
	}
}
