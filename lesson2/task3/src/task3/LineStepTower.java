package task3;

public class LineStepTower implements ILineStep{

	public LineStepTower() {
		System.out.println("LineStep for Tower is ready");
	}
	
	@Override
	public IProductPart buildProductPart() {
		System.out.println("Part Tower has been created");
		return new ProductPartTower();
	}

}
