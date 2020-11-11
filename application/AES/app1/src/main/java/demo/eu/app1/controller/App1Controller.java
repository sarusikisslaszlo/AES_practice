package demo.eu.app1.controller;

import demo.eu.app1.service.ReverseService;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class App1Controller {

  private static final String key = "aesEncryptionKey";
  private static final String initVector = "encryptionIntVec";

  @Autowired
  private ReverseService reverseService;

  @RequestMapping("/")
  public String getCurrentTime() {
    String value = "String";

    try {
      IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector.getBytes("UTF-8"));
      SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

      value = String.valueOf(reverseService.reverseString(value));
      byte[] encrypted = cipher.doFinal(value.getBytes());
      return Base64.encodeBase64String(encrypted);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
