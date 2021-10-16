package ai.datafocus.plugins.qst;

@QuarkusTest
public class MiscTest {

  
  @Test
  void encode() {
    byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
    String decodedString = new String(decodedBytes);
  }
}
