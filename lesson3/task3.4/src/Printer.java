
public class Printer {

	public static void print(Object[] array) {
		for (Object item : array) {
			if (item != null) print(item);
		}
		print("");
	}

	public static void print(Object object) {
		System.out.println(object);
	}
}
