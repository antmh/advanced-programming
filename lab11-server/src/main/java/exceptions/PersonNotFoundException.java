package exceptions;

public class PersonNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException() {
		super("Person not found");
	}
}
