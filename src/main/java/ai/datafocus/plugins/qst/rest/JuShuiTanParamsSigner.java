package ai.datafocus.plugins.qst.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ai.datafocus.plugins.qst.dto.DcsPluginInstance.InstanceVars;


// @Builder(buildMethodName = "builderInternal")
public class JuShuiTanParamsSigner {

  public static String sign(InstanceVars jstPartnerData, String method, long ts) {
    String beforeMd5 =
        method
            + jstPartnerData.getPartnerid()
            + "token"
            + jstPartnerData.getToken()
            + "ts"
            + ts
            + jstPartnerData.getPartnerkey();
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
    md.update(beforeMd5.getBytes());
    byte[] digest = md.digest();

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < digest.length; i++) {
      if ((0xff & digest[i]) < 0x10) {
        sb.append('0');
      }
      sb.append(Integer.toHexString(0xff & digest[i]));
    }
    return sb.toString();
  }

  // public static class JuShuiTanParamsBuilder {
  //   public JuShuiTanParams build() {
  //     JuShuiTanParams foo = this.builderInternal();
  //     foo.generateSign();
  //     return foo;
  //   }
  // }
}
