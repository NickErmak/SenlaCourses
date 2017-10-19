public class Reader extends AbstractReader {

	private AbstractBook[] bookList;

	public Reader(String name, String surname, int id, int maxBook) {
		super(name, surname, id);
		bookList = new Book[maxBook];
	}

	public AbstractBook[] getBookList() {
		return bookList;
	}

	public void setBookList(AbstractBook[] bookList) {
		this.bookList = bookList;
	}
	
}
