import java.util.ArrayList;

public class Adult extends Patient{
	
	private Adult spouse;
	private String occupation;
	private ArrayList<Child> childrenList;
	
	public Adult(String name, String surname, short age, ArrayList<Vaccination> vaccinationList, 
	ArrayList<Disease> diseaseList, Adult spouse, String occupation, ArrayList<Child> childrenList){
		super(name, surname, age, vaccinationList, diseaseList);
		System.out.println("Class Adult");
		this.spouse = spouse;
		this.occupation = occupation;
		this.childrenList = childrenList;
	}
	
	public String toString(){
		return getName()+" "+getSurname();
	}
	
	public Adult getSpouse(){
		return spouse;
	}
	
	public String getOccupation(){
		return occupation;
	}
	
	public ArrayList<Child> getChildren(){
		return childrenList;
	}
	
		
	protected void setSpouse(Adult spouse){
		this.spouse = spouse;
	}
	
	protected void setOccupation(String occupation){
		this.occupation = occupation;
	}
	
	protected void addChild(Child newChild){
		childrenList.add(newChild);
	}	
}
		
	