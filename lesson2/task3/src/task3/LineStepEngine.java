package task3;

public class LineStepEngine implements ILineStep{

	public LineStepEngine() {
		System.out.println("LineStep for Engine is ready");
	}
	
	@Override
	public IProductPart buildProductPart() {
		System.out.println("Part Engine has been created");
		return new ProductPartEngine();
	}

}
