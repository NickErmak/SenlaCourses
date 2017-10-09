import java.util.Date;

public class Vaccination{
	
	private String name;
	private Date date;
	private short validityPeriod;
	
	public Vaccination(String name, Date date, short validityPeriod){
		System.out.println("Class Vaccination");
		this.name = name;
		this.date = date;
		this.validityPeriod = validityPeriod;
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
	
	public short getValidityPeriod(){
		return validityPeriod;
	}
	
	protected void setName(String name){
		this.name = name;
	}
	
	protected void setDate(Date date){
		this.date = date;
	}
	
	protected void setValidityPeriod(short validityPeriod){
		this.validityPeriod = validityPeriod;
	}	
}	