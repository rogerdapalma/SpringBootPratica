package App.Projeto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; //predefinido status.ok == 200

import org.springframework.boot.test.web.client.TestRestTemplate;
import App.Projeto.greetings.recordsPractice.output.Greeting;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //auto configura
public class GreetingResourceTests {

    @Autowired // injeta
    private MockMvc tools;
    @Autowired
    private TestRestTemplate embeddedServer;

    // Teste de unidade => Ele zomba da solicitação e resposta do servlet por meio do seu endpoint
    @Test
    public void GetHellowSucess() throws Exception {
        Greeting objectResponse = new Greeting(1, "Hello World!");
        tools.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))// construiu requisicao
                .andExpect(status().isOk()); //espera resultado status 200 ou 201
    //            .andExpect(content().string(equalTo(objectResponse)));

    }

    //Teste de integração => crie um servidor embutido para texto
    @Test
    public void GetHelloSucessIntegrationTest() throws Exception {
        //resposta que deve ser comparada
        Greeting objectResponse = new Greeting(1, "Hello World!");
        // executa a url solicitada , em porta aleatoria
        ResponseEntity<Greeting> response = embeddedServer.getForEntity("/hello", Greeting.class);
        // confirmacao que a resposta adquirida que é igual a resposta criada
        assertThat(response.getBody()).isEqualTo(objectResponse);


    }
}
