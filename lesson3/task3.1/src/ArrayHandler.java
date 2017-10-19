import java.util.Arrays;
import java.util.Collections;

public class ArrayHandler {
	
	private static void sortArrayAscending(String [] array) {
		 Arrays.sort(array);
	}
	
	private static void sortArrayDescending(String [] array) {
		Arrays.sort(array, Collections.reverseOrder());
	}
	
	public static void sortArray(String[] array, boolean isAscending) {
		if (isAscending) sortArrayAscending(array);
		else sortArrayDescending(array);		
	}

	public static void showArray(String[] array) {
		for (String arrayItem : array)
			System.out.println(arrayItem);
	}

}