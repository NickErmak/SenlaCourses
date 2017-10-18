public class Reader extends AbstractReader {

	private Book[] bookList;

	public Reader(String name, String surname, int maxBook) {
		super(name, surname);
		bookList = new Book[maxBook];
	}

	public void addBook(Book book) {
		for (int i = 0; i < bookList.length; i++)
			if (bookList[i] == null) {
				bookList[i] = book;
				break;
			}
		book.setReader(this);
	}

	public void deleteBook(Book book) {
		for (int i = 1; i <= bookList.length; i++)
			if (bookList[i] == book) {
				bookList[i] = null;
				refreshBookList(i);
				book.setReader(null);
				break;
			}
	}

	public void refreshBookList(int arrayStartIndex) {
		for (int i = arrayStartIndex; i < bookList.length - 1; i++) {
			bookList[i] = bookList[i + 1];
		}

	}

	public void showBookList() {
		System.out.println("\nBooks of " + getName() + " " + getSurname());
		for (Book book : bookList) {
			if (book == null)
				break;
			System.out.println(book);
		}
	}

}
