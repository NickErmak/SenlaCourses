import java.util.Date;

public class Disease{

	private String name;
	private Date date;
	private String doctor;
	
	public Disease(String name, Date date, String doctor){
		System.out.println("Class Disease");
		this.name = name;
		this.date = date;
		this.doctor = doctor;
	}
	
	public String toString(){
		return name;
	}
	
	public String getName(){
		return name;
	}

	public Date getDate(){
		return date;
	}
	
	public String getDoctor(){
		return doctor;
	}
	
	protected void setName(String name){
		this.name = name;
	}
	
	protected void setDate(Date date){
		this.date = date;
	}
	
	protected void setDoctor(String doctor){
		this.doctor = doctor;
	}	
}