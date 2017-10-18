public class SamsungTV70Inch extends SamsungTV{
	
	final private int weightOnePiece;
	
	public SamsungTV70Inch(int quantity) {
		super(quantity);
		weightOnePiece = 60;				
	}
	
	@Override
	public int getProductTotalWeight() {
		return weightOnePiece*getQuantity();
	}


}
