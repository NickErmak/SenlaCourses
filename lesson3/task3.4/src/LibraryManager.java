
public class LibraryManager implements ILibraryManager {

	private Library library;
	private int maxBookForReader;

	public LibraryManager(int maxBook, int maxReader, int maxBookForReader) {
		this.library = new Library(new AbstractBook[maxBook], new AbstractReader[maxReader]);
		this.maxBookForReader = maxBookForReader;
	}

	@Override
	public void addBook(String title, String author, int id) {
		AbstractBook[] bookList = library.getBookList();
		bookList[ArrayHandler.getFreeCellIndex(bookList)] = new Book(title, author, id);
	}

	@Override
	public void addReader(String name, String surname, int id) {
		AbstractReader[] readerList = library.getReaderList();
		readerList[ArrayHandler.getFreeCellIndex(readerList)] = new Reader(name, surname, id, maxBookForReader);
	}

	/*
	 * don't use method 'check array for empty cell' (that returns boolean) because
	 * in that case there will be two identical commands for searching. command like
	 * array[array.length] is not suitable: don't want to refresh array after every
	 * removing
	 */

	@Override
	public void setBookOnReader(int idReader, int idBook) {
		try {
			AbstractReader reader = getReader(idReader);
			AbstractBook book = getBook(idBook);
			AbstractBook[] bookList = reader.getBookList();
			int index = ArrayHandler.getFreeCellIndex(bookList);
			if (index != -1) {
				bookList[index] = book;
				book.setReader(reader);
			} else
				Printer.print("Reader isn't allowed to take more books");
		} catch (NullPointerException e) {
			Printer.print("id doesn't exist!");
		}

	}

	@Override
	public void removeBookFromReader(int idReader, int idBook) {
		AbstractBook[] bookList = getReader(idReader).getBookList();
		bookList[ArrayHandler.getElementIndex(idBook, bookList)] = null;
		getBook(idBook).setReader(null);
	}

	@Override
	public void showReaderBookList(int idReader) {
		AbstractReader reader = getReader(idReader);
		Printer.print(reader);
		Printer.print(reader.getBookList());
	}

	@Override
	public void showReaderOfBook(int idBook) {		
		showReaderOfBook(getBook(idBook));
	}
	
	public void showReaderOfBook(AbstractBook book) {
		String reader = "no current reader";
		if (book.getReader()!=null)
			reader = book.getReader().toString();
		Printer.print(book.toString() + reader + "\n");
	}

	@Override
	public void showLibraryBookList() {
		Printer.print("List of books:");
		Printer.print(library.getBookList());
	}

	@Override
	public void showLibraryReaderList() {
		Printer.print("List of readers:");
		Printer.print(library.getReaderList());
	}

	private AbstractReader getReader(int id) {
		AbstractReader[] readerList = library.getReaderList();
		int index = ArrayHandler.getElementIndex(id, readerList);
		if (index != -1)
			return readerList[index];
		else
			return null;
	}

	private AbstractBook getBook(int id) {
		AbstractBook[] bookList = library.getBookList();
		int index = ArrayHandler.getElementIndex(id, bookList);
		if (index != -1)
			return bookList[index];
		return null;
	}

}
