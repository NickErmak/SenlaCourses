public class Storage implements IStorage {

	private AProduct[] productList;

	public Storage(AProduct[] product) {
		this.productList = product;
	}

	@Override
	public int getTotalWeight() {
		int totalWeight = 0;
		for (AProduct product : productList)
			totalWeight += product.getProductTotalWeight();
		return totalWeight;
	}

	@Override
	public void showTotalWeight() {
		System.out.println("Total weight of all products = " + getTotalWeight());

	}

}
