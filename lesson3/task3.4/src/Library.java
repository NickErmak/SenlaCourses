public class Library implements ILibrary {

	Book[] bookList;
	Reader[] readerList;
	final int maxBook;

	public Library(Book[] bookList, Reader[] readerList, int maxBook) {
		this.bookList = bookList;
		this.readerList = readerList;
		this.maxBook = maxBook;
	}



	@Override
	public void addBook(Book book) {
		for (int i = 0; i < bookList.length; i++)
			if (bookList[i] == null) {
				bookList[i] = book;
				break;
			}
	}

	@Override
	public void addReader(String name, String surname) {
		for (int i = 0; i < readerList.length; i++)
			if (readerList[i] == null) {
				readerList[i] = new Reader(name, surname, maxBook);
				break;
			}
	}

	public Reader getReader(String name, String surname) {
		for (int i = 0; i <= readerList.length; i++) {
			if (name == readerList[i].getName() && surname == readerList[i].getSurname())
				return readerList[i];
		}
		return null;
	}

	@Override
	public void showReaders() {
		System.out.println("\nList of readers:");
		for (Reader reader : readerList) {
			if (reader == null)
				break;
			System.out.println(reader);
		}
	}

	@Override
	public void showBooks() {
		System.out.println("\nList of books:");
		for (Book book : bookList) {
			if (book == null)
				break;
			String reader;
			if (book.getReader()==null) reader ="no current reader";
			else reader = book.getReader().toString();
			System.out.println(book+" "+reader);

		}
	}

}
