package demo.eu.app2.controller;

import demo.eu.app2.service.ReverseService;
import java.util.Arrays;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class App2Controller {

  private static final String key = "aesEncryptionKey";
  private static final String initVector = "encryptionIntVec";

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ReverseService reverseService;

  @RequestMapping("/")
  public String getStringFromApp1() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<String>(headers);

    try {
      IvParameterSpec  ivParameterSpec = new IvParameterSpec(initVector.getBytes("UTF-8"));
      SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE,secretKeySpec, ivParameterSpec);
      byte[] original = cipher.doFinal(Base64.decodeBase64(restTemplate.exchange("http://localhost:8080/", HttpMethod.GET, entity, String.class).getBody()));

      System.out.println(new String(reverseService.reverseString(original)));

      return new String(original);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
