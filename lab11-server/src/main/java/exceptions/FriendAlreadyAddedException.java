package exceptions;

public class FriendAlreadyAddedException extends Exception {
	private static final long serialVersionUID = 1L;

	public FriendAlreadyAddedException() {
		super("Friend already added");
	}
}
