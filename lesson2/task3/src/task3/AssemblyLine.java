package task3;

public class AssemblyLine implements IAssemblyLine{
	
	ILineStep lineStep1, lineStep2, lineStep3;	
	
	public AssemblyLine(ILineStep lineStep1, ILineStep lineStep2, ILineStep lineStep3) {		
		this.lineStep1 = lineStep1;
		this.lineStep2 = lineStep2;
		this.lineStep3 = lineStep3;	
		System.out.println("\nAssemblyLine is ready!\n");
	}
	
	@Override
	public IProduct assembleProduct(IProduct product) {
		System.out.println("Creating and installing:\n");
		product.installFirstPart(lineStep1.buildProductPart());
		product.installSecondPart(lineStep2.buildProductPart());
		product.installThirdPart(lineStep3.buildProductPart());
		System.out.println("\nProduct is ready!");
		return product;
	}

}
