package task4;

public abstract class AbstractBook {

	private String title;
	private String author;
	private int yearEdition;

	public AbstractBook(String title, String author, int yearEdition) {
		this.title = title;
		this.author = author;
		this.yearEdition = yearEdition;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYearEdition() {
		return yearEdition;
	}

	public void setYearEdition(int yearEdition) {
		this.yearEdition = yearEdition;
	}

	@Override
	public String toString() {
		return "Book [" + title + ", " + author + ", " + yearEdition + " edition]";
	}

	abstract public Reader getReader();

	abstract public void setReader(Reader reader);
}
