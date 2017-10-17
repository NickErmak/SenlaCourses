package task1;

import java.util.Arrays;

public class ArrayMod {
	
	//can rebuild methods to non-static and use created object ArrayMod
	public static void sortArray(String[] array) {
		Arrays.sort(array);
	}

	public static void showArray(String[] array) {
		for (String arrayItem : array)
			System.out.println(arrayItem);
	}

}