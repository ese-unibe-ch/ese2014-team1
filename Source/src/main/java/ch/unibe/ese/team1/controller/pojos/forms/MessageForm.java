package ch.unibe.ese.team1.controller.pojos.forms;

import org.hibernate.validator.constraints.NotBlank;

public class MessageForm {
	
	@NotBlank(message = "Required")
	private String receiver;
	
	@NotBlank(message = "Required")
	private String subject;
	
	@NotBlank(message = "Required")
	private String message;
	
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}