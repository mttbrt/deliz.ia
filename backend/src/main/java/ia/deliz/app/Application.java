package ia.deliz.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "deliz.ia REST APIs"))
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
