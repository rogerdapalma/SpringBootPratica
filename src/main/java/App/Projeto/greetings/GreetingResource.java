package App.Projeto.greetings;

import org.springframework.web.bind.annotation.*;
import App.Projeto.greetings.recordsPractice.output.Greeting;
import App.Projeto.greetings.recordsPractice.input.HelloPostInputDto;
import App.Projeto.greetings.recordsPractice.output.HelloPostOutputDto;

import java.util.concurrent.atomic.AtomicLong;

@RestController//Definindo calsse como rest
public class GreetingResource {
  private String greeting = "Hello %s!";
  private final AtomicLong counter = new AtomicLong();//contador

  @GetMapping("/hello") //localhost:8080/hello
  public Greeting greeting(
          @RequestParam(value = "name" , defaultValue = "world") String name
  ) {
      return new Greeting(counter.incrementAndGet(), String.format(greeting, name));
  }

  @PostMapping("/helloPost") //localhost:8080/helloPost
  // output = saida esperada
  public HelloPostOutputDto greetingPost(
          // input = body
          @RequestBody HelloPostInputDto input
  ) {
    return new HelloPostOutputDto(input.name(), input.money());
  }
}
