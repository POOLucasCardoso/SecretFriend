
public abstract class Mensage {
	private String text;
	private String emailSender;
	private boolean anonima;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public Mensage(String text, String emailSender, boolean anonima) {
		this.text = text;
		this.emailSender = emailSender;
		this.anonima = anonima;
	}

	public boolean isAnonima() {
		return anonima;
	}
	
	public abstract String showCompleteText();

}
