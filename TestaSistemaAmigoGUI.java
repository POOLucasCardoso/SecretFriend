import javax.swing.JOptionPane;

public class TestaSistemaAmigoGUI {

	public static void main(String[] args) throws AmigoInexistenteException,AmigoNaoSorteadoException {
		FriendSys sys = new FriendSys();
		int quantMsg = Integer.parseInt(JOptionPane.showInputDialog("Quantas mensagens o sistema suporta?"));
		int quantAgm = Integer.parseInt(JOptionPane.showInputDialog("Quantos amigos devem ter na brincadeira?"));
		for(int i = 1; i<=quantAgm; i++) {
			String nome = JOptionPane.showInputDialog("Qual nome do "+i+"° participante");
			String email = JOptionPane.showInputDialog("Qual e-mail do "+i+"° participante");
			sys.addFriend(nome, email);
		}
		for(Friend f: sys.getFriends()) {
			sys.secretFriendConfig(f.getEmail(), JOptionPane.showInputDialog("O sorteado de "+f.getName()+" foi:"));
		}
		for(int i = 0; i<quantMsg; i++) {
			String email = JOptionPane.showInputDialog("Quem deseja enviar a "+i+"° mensagem?/nDigite o email da pessoa quer enviar");
			int isAnonima = JOptionPane.showConfirmDialog(null, "A mensagem é anônima?", "", 0);
			boolean bool;
			if (isAnonima == 0) {
				bool = true;
			}
			else {
				bool = false;
			}
			String texto = JOptionPane.showInputDialog("Digite a mensagem:");
			sys.sendMensageToEveryone(texto, email, bool);
			if(JOptionPane.showConfirmDialog(null, "Deseja parar de enviar mensagens?", "", 0) == 0) {break;}
		}

	}

}
