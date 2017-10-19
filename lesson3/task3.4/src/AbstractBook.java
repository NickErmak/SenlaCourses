
public abstract class AbstractBook extends IdElement {

	private String title;
	private String author;

	public AbstractBook(String title, String author, int id) {
		super(id);
		this.title = title;
		this.author = author;
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

	@Override
	public String toString() {
		String reader = "no current reader";
		if (getReader()!=null) reader = getReader().toString();
		return "Book [title=" + title + ", author=" + author + ", id=" + id + "] "+ reader;
	}

	abstract public AbstractReader getReader();

	abstract public void setReader(AbstractReader reader);

}
