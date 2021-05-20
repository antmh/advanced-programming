package exceptions;

public class PersonAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public PersonAlreadyExistsException() {
		super("Person already exists");
	}
}
