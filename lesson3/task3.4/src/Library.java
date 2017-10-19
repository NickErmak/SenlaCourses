
public class Library {

	private AbstractBook[] bookList;
	private AbstractReader[] readerList;

	public Library(AbstractBook[] bookList, AbstractReader[] readerList) {
		this.bookList = bookList;
		this.readerList = readerList;
	}

	public AbstractBook[] getBookList() {
		return bookList;
	}

	public void setBookList(AbstractBook[] bookList) {
		this.bookList = bookList;
	}

	public AbstractReader[] getReaderList() {
		return readerList;
	}

	public void setReaderList(AbstractReader[] readerList) {
		this.readerList = readerList;
	}

}
