package social;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGES")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private int id;

	@ManyToOne
	@JoinColumn(name = "SENDER_FRIEND_ID")
	private Person sender;

	@ManyToOne
	@JoinColumn(name = "RECIEVER_FRIEND_ID")
	private Person reciever;

	@Column(name = "content", nullable = false)
	private String content;
	
	public Message() {
	}

	public Message(Person sender, Person reciever, String content) {
		this.sender = sender;
		this.reciever = reciever;
		this.content = content;
	}

	public String getSender() {
		return sender.getName();
	}

	public String getReciever() {
		return reciever.getName();
	}

	public String getContent() {
		return content;
	}
}
