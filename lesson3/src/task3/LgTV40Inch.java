package task3;

public class LgTV40Inch extends LgTV {

	final private int weightOnePiece;
	
	public LgTV40Inch(int quantity) {
		super(quantity);
		weightOnePiece = 20;				
	}
	
	@Override
	public int getProductTotalWeight() {
		return weightOnePiece*getQuantity();
	}
}
