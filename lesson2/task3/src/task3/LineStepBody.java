package task3;

public class LineStepBody implements ILineStep{

	public LineStepBody() {
		System.out.println("LineStep for Body is ready");
	}
	
	@Override
	public IProductPart buildProductPart() {
		System.out.println("Part Body has been created");
		return new ProductPartBody();
	}

}
