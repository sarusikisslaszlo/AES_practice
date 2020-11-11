package demo.eu.app1.service;

import org.springframework.stereotype.Service;

@Service
public class ReverseService {

  public StringBuilder reverseString(String str) {
    StringBuilder builder = new StringBuilder();

    builder.append(str);
    return builder.reverse();
  }

}
