
public class Test {

	private static void addBook(ILibraryManager libManager) {
		libManager.addBook("1984", "Charles Baudelaire", 111);
		libManager.addBook("The flowers of evil", "Charles Baudelaire", 357);
		libManager.addBook("Alice's adventures in wonderland", "Lewis Carroll", 144);
		libManager.addBook("The Catcher in the Rye", "J. D. Salinger", 12);
		libManager.addBook("The Glass Bead Game", "Hermann Hesse", 7);
	}

	private static void addReader(ILibraryManager libManager) {
		libManager.addReader("John", "Smith", 13);
		libManager.addReader("Mary", "Jane", 55);
	}

	private static void setBook(ILibraryManager libManager) {
		libManager.setBookOnReader(13, 111);
		libManager.setBookOnReader(13, 357);
		libManager.setBookOnReader(13, 144);
	}

	public static void main(String[] args) {

		// set max readers, max books in library and max quantity of books for 1 reader
		// in constructor
		ILibraryManager libManager = new LibraryManager(1000, 100, 5);

		// add books to library with id
		addBook(libManager);

		// add readers to library with id
		addReader(libManager);

		// add books to definite reader
		setBook(libManager);

		// show book at reader with id=13
		libManager.showReaderBookList(13);

		// check who reads the exact book with id=357
		libManager.showReaderOfBook(357);

		libManager.removeBookFromReader(13, 357);

		// check who reads the exact book
		libManager.showReaderOfBook(357);

		libManager.showReaderBookList(13);

		libManager.showLibraryBookList();
		libManager.showLibraryReaderList();
	}

}
