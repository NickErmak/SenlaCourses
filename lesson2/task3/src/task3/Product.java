package task3;

public class Product implements IProduct{
	IProductPart firstProductPart, secondProductPart, thirdProductPart;
	
	private static final String SUCCESSMESSAGE = "Component has been installed!";

	@Override
	public void installFirstPart(IProductPart firstProductPart) {
		this.firstProductPart = firstProductPart;		
		System.out.println(SUCCESSMESSAGE);
	}

	@Override
	public void installSecondPart(IProductPart secondProductPart) {
		this.secondProductPart = secondProductPart;		
		System.out.println(SUCCESSMESSAGE);
	}

	@Override
	public void installThirdPart(IProductPart thirdProductPart) {
		this.thirdProductPart = thirdProductPart;	
		System.out.println(SUCCESSMESSAGE);
	}

}
