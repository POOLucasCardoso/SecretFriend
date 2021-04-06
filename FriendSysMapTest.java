import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FriendSysMapTest {

	FriendSysMap sistema;
  
  @BeforeEach
  void setUp()  {
    this.sistema = new FriendSysMap();
  }

  @Test
  void testSistemaAmigo() {
    assertTrue(sistema.showAllMensages().isEmpty());
    assertThrows(AmigoInexistenteException.class, 
        ()-> sistema.searchFriend("ayla@teste.com"));
  }

  @Test
  void testPesquisaECadastraAmigo() {
    try {
      sistema.searchFriend("ayla@teste.com");
      fail("Deveria falhar pois não existe ainda");
    } catch (AmigoInexistenteException e) {
      //Ok
    }
    try {
      sistema.addFriend("ayla", "ayla@teste.com");
      Friend a = sistema.searchFriend("ayla@teste.com");
      assertEquals("ayla", a.getName());
      assertEquals("ayla@teste.com", a.getEmail());
    } catch (AmigoJaExisteException | AmigoInexistenteException  e) {
      fail("Não deveria lançar exceção");
    } 
    
    
  }

  @Test
  void testEnviarMensagemParaTodos() {
    assertTrue(sistema.showAllMensages().isEmpty());
    sistema.sendMensageToEveryone("texto", "ayla@dcx.ufpb.br", true);
    List<Mensage> mensagensAchadas = sistema.showAllMensages();
    assertTrue(mensagensAchadas.size()==1);
    assertTrue(mensagensAchadas.get(0).getEmailSender().equals("ayla@dcx.ufpb.br"));
  }

  @Test
  void testEnviarMensagemParaAlguem() {
    assertTrue(sistema.showAllMensages().isEmpty());
    sistema.sendMensageToSomeone("texto", "ayla@dcx.ufpb.br", "rodrigo@dcx.ufpb.br", true);
    List<Mensage> mensagensAchadas = sistema.showAllMensages();
    assertEquals(1, mensagensAchadas.size());
    assertTrue(mensagensAchadas.get(0) instanceof MensageToSomeone);
    assertTrue(mensagensAchadas.get(0).getText().equals("texto"));
  }

  @Test
  void testPesquisaMensagensAnonimas() {
    assertTrue(sistema.showAllMensages().isEmpty());
    sistema.sendMensageToSomeone("texto 1", "ayla@dcx.ufpb.br", "rodrigo@dcx.ufpb.br", false);
    assertTrue(sistema.showAllAnonimaMensages().isEmpty());
    sistema.sendMensageToSomeone("texto 2", "ayla@dcx.ufpb.br", "rodrigo@dcx.ufpb.br", true);
    assertTrue(sistema.showAllAnonimaMensages().size()==1);
  }

  @Test
  void testPesquisaTodasAsMensagens() {
    assertTrue(sistema.showAllMensages().isEmpty());
    sistema.sendMensageToSomeone("texto 1", "ayla@dcx.ufpb.br", "rodrigor@dcx.ufpb.br", false);
    assertTrue(sistema.showAllMensages().size()==1);
    sistema.sendMensageToSomeone("texto 2", "ayla@dcx.ufpb.br", "rodrigor@dcx.ufpb.br", true);
    assertTrue(sistema.showAllMensages().size()==2);
  }

  @Test
  void testPesquisaAmigoEConfiguraAmigoSecretoDe() {
    assertThrows(AmigoInexistenteException.class, 
        ()-> sistema.searchSecretFriendOf("ayla@dcx.ufpb.br"));
    try {
      sistema.addFriend("Ayla", "ayla@dcx.ufpb.br");
      sistema.addFriend("Ana", "ana@dcx.ufpb.br");
      sistema.secretFriendConfig("ayla@dcx.ufpb.br", "ana@dcx.ufpb.br");
      sistema.secretFriendConfig("ana@dcx.ufpb.br", "ayla@dcx.ufpb.br");
      assertEquals("ana@dcx.ufpb.br", sistema.searchSecretFriendOf("ayla@dcx.ufpb.br"));
      assertEquals("ayla@dcx.ufpb.br", sistema.searchSecretFriendOf("ana@dcx.ufpb.br"));
    } catch (AmigoInexistenteException | AmigoJaExisteException | AmigoNaoSorteadoException e) {
      fail("Não deveria lançar exceção");
    }
  }


}