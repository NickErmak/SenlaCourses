package task4;

public class Test {

	// looking forward to collections
	public static void main(String[] args) {

		// set max total of books, readers and max books for one reader via constructor
		Library library = new Library(new Book[100], new Reader[100], 5);

		Book book1984 = new Book("1984", "George Orwell", 1948);
		Book bookTheFlowersOfEvil = new Book("The flowers of evil", "Charles Baudelaire", 1857);
		Book bookAliceInWondeland = new Book("Alice's adventures in wonderland", "Lewis Carroll", 1865);

		library.addBook(book1984);
		library.addBook(bookTheFlowersOfEvil);
		library.addBook(bookAliceInWondeland);

		library.addReader("John", "Smith");
		library.addReader("Mary", "Jane");
		Reader readerSmith = library.getReader("John", "Smith");

		readerSmith.addBook(book1984);
		readerSmith.addBook(bookTheFlowersOfEvil);
		readerSmith.addBook(bookAliceInWondeland);
		readerSmith.showBookList();

		readerSmith.deleteBook(bookTheFlowersOfEvil);
		readerSmith.showBookList();
		
		//check who reads the exact book
		System.out.println("\n" + book1984.getReader());

		library.showBooks();
		library.showReaders();
	}

}
