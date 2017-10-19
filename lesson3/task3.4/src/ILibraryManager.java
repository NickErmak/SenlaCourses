public interface ILibraryManager {

	public void addBook(String title, String author, int id);

	public void addReader(String name, String surname, int id);

	public void setBookOnReader(int idReader, int idBook);

	public void removeBookFromReader(int idReader, int idBook);

	public void showReaderBookList(int idReader);

	public void showReaderOfBook(int idBook);

	public void showLibraryBookList();

	public void showLibraryReaderList();

}
