public class Test {

	public static void main(String[] args) {

		String[] array = { "Nick", "Jorge", "Adolinda", "John" };

		// ascending sort array
		ArrayHandler.sortArray(array, true);
		ArrayHandler.showArray(array);

		// descending sort array
		ArrayHandler.sortArray(array, false);
		ArrayHandler.showArray(array);

	}

}
