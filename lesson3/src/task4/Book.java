package task4;

public class Book extends AbstractBook{
	
	private Reader reader;
	
	public Book(String title, String author, int yearEdition) {
		super(title, author, yearEdition);
	}	
	
	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}	
	
}
