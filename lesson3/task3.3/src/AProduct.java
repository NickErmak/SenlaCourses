public abstract class AProduct {

	private int quantity;

	public AProduct(int quantity) {
		this.quantity = quantity;
	}

	abstract public int getProductTotalWeight();

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
