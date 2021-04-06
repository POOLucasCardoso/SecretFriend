
public class MensageToSomeone extends Mensage {
	private String emailRecipient;

	public MensageToSomeone(String text, String emailSender, boolean anonima, String emailRecipient) {
		super(text, emailSender, anonima);
		this.emailRecipient = emailRecipient;
	}

	public String getEmailRecipient() {
		return emailRecipient;
	}

	public void setEmailRecipient(String emailRecipient) {
		this.emailRecipient = emailRecipient;
	}

	@Override
	public String showCompleteText() {
		if(isAnonima()){
			return "Mensagem para "+this.emailRecipient+". Texto: "+super.getText();
		}
		else {
			return "Mensagem de "+super.getEmailSender()+" Para "+this.emailRecipient+". Texto: "+super.getText();
		}

		
	}

}
