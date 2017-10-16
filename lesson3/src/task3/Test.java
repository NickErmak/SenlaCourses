package task3;

public class Test {

	public static void main(String[] args) {

		// set quantity of each product via constructor
		AProduct[] productList = { new LgTV40Inch(8), new LgTV55Inch(12), new SamsungTV70Inch(5) };
		// add this array to storage and calculate total weight
		new Storage(productList).showTotalWeight();

	}

}
