package task3;

public class LgTV55Inch extends LgTV{

	final private int weightOnePiece;
	
	public LgTV55Inch(int quantity) {
		super(quantity);
		weightOnePiece = 40 ;				
	}
	
	@Override
	public int getProductTotalWeight() {
		return weightOnePiece*getQuantity();
	}
}
