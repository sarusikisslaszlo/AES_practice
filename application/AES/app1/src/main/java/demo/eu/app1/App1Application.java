package demo.eu.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class App1Application {

  public static void main(String[] args) {
    SpringApplication.run(App1Application.class, args);
  }

}
