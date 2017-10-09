import java.util.ArrayList;

public class Patient{
	
	private String name;
	private String surname;
	private short age;
	private ArrayList<Vaccination> vaccinationList;
	private ArrayList<Disease> diseaseList;
	
	public Patient(String name, String surname, short age, ArrayList<Vaccination> vaccinationList, ArrayList<Disease> diseaseList){
		System.out.println("Class Patient");
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.vaccinationList = vaccinationList;
		this.diseaseList = diseaseList;
	}
	
	public String getName(){
		return name;
	}
	
	public String getSurname(){
		return surname;
	}
	
	public short getAge(){
		return age;
	}
	
	public ArrayList<Vaccination> getVaccinationList(){
		return vaccinationList;
	}
	
	public ArrayList<Disease> getDiseaseList(){
		return diseaseList;
	}
	
	protected void setName(String name){
		this.name = name;
	}
	
	protected void setSurname(String surname){
		this.surname = surname;
	}
	
	protected void setAge (short age){
		this.age = age;
	}
	
	protected void addVaccination(Vaccination newVaccination){
		vaccinationList.add(newVaccination);
	}
	
	protected void addDisease(Disease newDisease){
		diseaseList.add(newDisease);
	}		
}	
	
		