
public class ArrayHandler {

	public static boolean hasArrayFreeCell(Object [] array) {
		if (array[array.length - 1] == null) return true;
		else return false;
	}
	
	public static int getFreeCellIndex(Object [] array) {
		for (int i = 0; i < array.length; i++)
			if (array[i] == null)
				return i;
		return -1;		
	}
}
