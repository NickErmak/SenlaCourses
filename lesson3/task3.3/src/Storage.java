public class Storage implements IStorage {

	private AProduct[] productStorage;

	public Storage(int cellQuantity) {
		productStorage = new AProduct[cellQuantity];
	}

	@Override
	public int getTotalWeight() {
		int totalWeight = 0;
		for (AProduct product : productStorage)
			if (product != null) totalWeight += product.getWeight();
		return totalWeight;
	}

	@Override
	public void showTotalWeight() {
		System.out.println("Total weight of products on storage = " + getTotalWeight());
	}

	@Override
	public void addProduct(AProduct product) {
		if (ArrayHandler.hasArrayFreeCell(productStorage)) {
			productStorage[ArrayHandler.getFreeCellIndex(productStorage)] = product;
			System.out.println(product + " successfully added");}
		else
			System.out.println("Can't add product: storage is full");
	}

}
