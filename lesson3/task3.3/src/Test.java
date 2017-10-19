public class Test {
	
	private static void fillStorage (IStorage storage) {
		AProduct[] productList = { new Lg("LG OLED TV 65\"", 20), new Lg("LG OLED TV 77\"", 25), new Samsung("SAMSUNG UHD 75\"", 22),    
				new Lg("LG OLED TV 65\"", 20), new Lg("LG OLED TV 65\"", 20), new Samsung("SAMSUNG Q6F 49\"", 15), 
				new Lg("LG OLED TV 77\"", 25), new Samsung("SAMSUNG Q6F 49\"", 15), new Samsung("SAMSUNG UHD 75\"", 22)};			
		
		for (AProduct product: productList) {
			storage.addProduct(product);
		}
	}

	public static void main(String[] args) {

		//set limit quantity of items via constructor
		Storage storage = new Storage(8);
		
		// fill storage to the limit
		fillStorage(storage);
		
		storage.showTotalWeight();
	}

}
