public abstract class AbstractReader extends IdElement {

	private String name;
	private String surname;

	public AbstractReader(String name, String surname, int id) {
		super(id);
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
		return "Reader [name=" + name + ", surname=" + surname + ", id=" + id + "]";
	}

	abstract public AbstractBook[] getBookList();

	abstract public void setBookList(AbstractBook[] bookList);

}
