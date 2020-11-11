package demo.eu.app2.service;

import org.springframework.stereotype.Service;

@Service
public class ReverseService {

  public byte[] reverseString(byte[] str) {
    byte[] result = new byte[str.length];

    for(int i = 0; i < str.length; i++) {
      result[i] = str[str.length - i - 1];
    }
    return result;
  }
}
