public abstract class AProduct {

	private String model;
	private int weight;
	
	public AProduct(String model, int weight) {		
		this.model = model;
		this.weight = weight;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Product [model=" + model + "]";
	}	
	
}
