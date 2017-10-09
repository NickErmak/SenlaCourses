import java.util.ArrayList;

public class Child extends Patient{
	
	private String school;
	private Adult mom;
	private Adult dad;
	
	public Child(String name, String surname, short age, ArrayList<Vaccination> vaccinationList, 
	ArrayList<Disease> diseaseList, String school, Adult mom, Adult dad){
		super(name, surname, age, vaccinationList, diseaseList);
		System.out.println("Class Child");
		this.school = school;
		this.mom = mom;
		this.dad = dad;
	}
	
	public String toString(){
		String report;
		report = "\nReport:\n"+"name - "+getName()+"\nsurname - "+getSurname()+"\nage - "+getAge()+
		"\nSchool - "+getSchool()+"\nMom - "+mom+"\nDad - "+dad+"\n\nVaccinations:\n"+getVaccinationList().toString()+
		"\n\nDiseases:\n"+getDiseaseList().toString();
		return report;
	}
	
	public String getSchool(){
		return school;
	}
	
	public Adult getMom(){
		return mom;
	}
	
	public Adult getDad(){
		return dad;
	}
	
	protected void setSchool(String school){
		this.school = school;
	}
	
	protected void setMom(Adult mom){
		this.mom = mom;
	}
	
	protected void setDad(Adult dad){
		this.dad = dad;
	}	
}