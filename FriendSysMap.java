import java.util.LinkedList;
import java.util.HashMap;

public class FriendSysMap {

	private LinkedList<Mensage> mensages = new LinkedList<Mensage>();
	private HashMap<String,Friend> friends = new HashMap<String,Friend>();
	
	public void addFriend(String name, String email) throws AmigoJaExisteException {
		if (this.friends.containsKey(email)) {
			throw new AmigoJaExisteException("Já existe alguém cadastrado com esse email");
		}
		this.friends.put(email, new Friend(name, email, null));
	}
	
	public LinkedList<Friend> getFriends() {
		LinkedList<Friend> friends = new LinkedList<Friend>();
		for(Friend f:this.friends.values()) {
			friends.add(f);
		}
		return friends;
	}

	public Friend searchFriend(String email) throws AmigoInexistenteException {
		if(this.friends.containsKey(email)) {
			return this.friends.get(email);
		}else {
			throw new AmigoInexistenteException("Ningém está cadastrado com esse e-mail");
		}
	}
	
	public void sendMensageToEveryone(String text, String emailSender, boolean isAnonima){
		this.mensages.add(new MensageToEveryone(text, emailSender, isAnonima));
	}
	
	public void sendMensageToSomeone(String text, String emailSender, String emailRecipient, boolean isAnonima){
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
