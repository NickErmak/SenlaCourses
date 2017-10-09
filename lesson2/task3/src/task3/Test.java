package task3;

public class Test {

	public static void main(String[] args) {
		
		AssemblyLine assemblyLinePanzer = new AssemblyLine(new LineStepBody(), 
				new LineStepEngine(), new LineStepTower());
		
		assemblyLinePanzer.assembleProduct(new Product());
		
	}

}
