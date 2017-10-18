public abstract class AbstractReader {

	private String name;
	private String surname;

	public AbstractReader(String name, String surname) {
		this.name = name;
		this.surname = surname;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Reader [" + name + " " + surname + "]";
	}

	abstract public void addBook(Book book);

	abstract public void deleteBook(Book book);

	abstract public void showBookList();

}
