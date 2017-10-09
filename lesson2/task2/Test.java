import java.util.ArrayList;
import java.util.Date;

public class Test{
	
	public static void main(String[] args){
		
		Adult adult1Jane = new Adult("Jane", "Smith", (short) 28, null, null, null, "teacher", new ArrayList<Child>());
		Adult adult2Piter = new Adult("Piter", "Smith", (short) 34, null, null, null, "builder", new ArrayList<Child>());
		
		Child childBob = new Child("Bob", "Smith", (short) 8, new ArrayList<Vaccination>(), 
			new ArrayList<Disease>(), "school ¹34", adult1Jane, adult2Piter);
	
		adult1Jane.addChild(childBob);
		adult2Piter.addChild(childBob);
	
		childBob.addVaccination(new Vaccination("Flu", new Date(2020, 10, 21), (short) 365));
		childBob.addDisease(new Disease("Angina", new Date(2018, 9 , 22), "Dr Gregory House"));
		
		System.out.println(childBob);
		
	}
}