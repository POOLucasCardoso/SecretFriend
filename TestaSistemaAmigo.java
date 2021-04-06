import java.util.LinkedList;

public class TestaSistemaAmigo {

	public static void main(String[]args) throws AmigoInexistenteException,AmigoNaoSorteadoException {
		FriendSys sys = new FriendSys();
		sys.addFriend("Maria", "maria@dcx");
		sys.addFriend("José", "jose@dcx");
		sys.secretFriendConfig("maria@dcx", "jose@dcx");
		sys.secretFriendConfig("jose@dcx", "maria@dcx");
		sys.sendMensageToSomeone("Oi gato", "maria@dcx", true, "jose@dcx");
		sys.sendMensageToEveryone("Olá", "maria@dcx", false);
		LinkedList<Mensage> mensages = sys.showAllAnonimaMensages();
		for(Mensage m: mensages) {
			System.out.println(m.showCompleteText());
		}
		if(sys.searchSecretFriendOf("maria@dcx").equals("jose@dcx")) {
			System.out.println("OK");
		}
	}

}
