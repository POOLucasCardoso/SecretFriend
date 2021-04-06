import java.util.LinkedList;

public class FriendSys {

	private LinkedList<Mensage> mensages = new LinkedList<Mensage>();
	private LinkedList<Friend> friends = new LinkedList<Friend>();
	
	public void addFriend(String name, String email) {
		this.friends.add(new Friend(name, email, null));
		
	}
	
	public LinkedList<Friend> getFriends() {
		return friends;
	}

	public Friend searchFriend(String email) throws AmigoInexistenteException {
		for(Friend f : this.friends){
			if (f.getEmail().equals(email)){
				return f;
			}
		}
		throw new AmigoInexistenteException("Ningém está cadastrado com esse e-mail");
	}
	
	public void sendMensageToEveryone(String text, String emailSender, boolean isAnonima) throws AmigoInexistenteException {
		this.searchFriend(emailSender);
		this.mensages.add(new MensageToEveryone(text, emailSender, isAnonima));
	}
	
	public void sendMensageToSomeone(String text, String emailSender, boolean isAnonima, String emailRecipient) throws AmigoInexistenteException {
		this.searchFriend(emailRecipient);
		this.searchFriend(emailSender);
		this.mensages.add(new MensageToSomeone(text, emailSender, isAnonima, emailRecipient));
	}
	
	public LinkedList<Mensage> showAllAnonimaMensages(){
		LinkedList<Mensage> tempList = new LinkedList<Mensage>();
		for(Mensage m: this.mensages) {
			if (m.isAnonima()) {
				tempList.add(m);
			}
		}
		return tempList;
	}
	
	public LinkedList<Mensage> showAllMensages(){
		return this.mensages;
	}
	
	public void secretFriendConfig(String friendMail, String secretMail) throws AmigoInexistenteException {
		Friend tempFriend = searchFriend(friendMail);
		searchFriend(secretMail);
		tempFriend.setFriendEmail(secretMail);
		
	}
	
	public String searchSecretFriendOf(String email) throws AmigoInexistenteException,AmigoNaoSorteadoException {
		Friend tempFriend = this.searchFriend(email);
		if(tempFriend.getFriendEmail() == null) {
			throw new AmigoNaoSorteadoException(tempFriend.getName()+" não possui um amigo secreto");
		}
		return tempFriend.getFriendEmail();
	}
	
}
