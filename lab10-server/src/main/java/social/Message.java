package social;

public class Message {
	private String content;
	private Person author;

	public Message() {
	}

	public Message(String content, Person author) {
		this.content = content;
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}
}
