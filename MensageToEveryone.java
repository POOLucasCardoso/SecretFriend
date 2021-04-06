
public class MensageToEveryone extends Mensage {

	public MensageToEveryone(String text, String emailSender, boolean anonima) {
		super(text, emailSender, anonima);
		
	}

	@Override
	public String showCompleteText() {
		if(isAnonima()) {
			return "Mensagem para todos: "+super.getText();
		}
		else {
			return "Mensagem de: "+super.getEmailSender()+ " para todos "+super.getText();
		}
	}

}
