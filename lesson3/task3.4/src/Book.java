
public class Book extends AbstractBook {

	private AbstractReader reader;

	public Book(String title, String author, int id) {
		super(title, author, id);
	}

	public AbstractReader getReader() {
		return reader;
	}

	public void setReader(AbstractReader reader) {
		this.reader = reader;
	}

}
