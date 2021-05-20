package spring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import exceptions.FriendAlreadyAddedException;
import exceptions.PersonAlreadyExistsException;
import exceptions.PersonNotFoundException;

@RestControllerAdvice
public class RestErrorHandler {
	@ExceptionHandler(value = { FriendAlreadyAddedException.class })
	@ResponseStatus(HttpStatus.CONFLICT)
	public String friendAlreadyAdded(FriendAlreadyAddedException exception) {
		return exception.getMessage();
	}

	@ExceptionHandler(value = { PersonAlreadyExistsException.class })
	@ResponseStatus(HttpStatus.CONFLICT)
	public String personAlreadyAdded(PersonAlreadyExistsException exception) {
		return exception.getMessage();
	}

	@ExceptionHandler(value = { PersonNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String friendAlreadyAdded(PersonNotFoundException exception) {
		return exception.getMessage();
	}
}
