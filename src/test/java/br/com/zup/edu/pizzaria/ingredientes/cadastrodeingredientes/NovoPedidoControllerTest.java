package br.com.zup.edu.pizzaria.ingredientes.cadastrodeingredientes;

import br.com.zup.edu.pizzaria.ingredientes.Ingrediente;
import br.com.zup.edu.pizzaria.pedidos.TipoDeBorda;
import br.com.zup.edu.pizzaria.pedidos.novopedido.EnderecoRequest;
import br.com.zup.edu.pizzaria.pedidos.novopedido.ItemRequest;
import br.com.zup.edu.pizzaria.pedidos.novopedido.NovoPedidoRequest;
import br.com.zup.edu.pizzaria.pizzas.Pizza;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class NovoPedidoControllerTest {

    @Autowired
    private MockMvc mvc;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    void deveCadastrarNovoPedido() throws Exception {

        final var pizza = new Pizza("mussarela", Arrays.asList(
                new Ingrediente("queijo", 1, new BigDecimal("10.0"))
        ));

        this.entityManager.persist(pizza);

        final var body = new NovoPedidoRequest(new EnderecoRequest("rua", "numero","complemento","cep"),
                Arrays.asList(new ItemRequest(pizza.getId(), TipoDeBorda.RECHEADA_CATUPIRY )));

        MockHttpServletRequestBuilder request = post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(body));


        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(redirectedUrlPattern("/api/pedidos/{\\d*}"));

    }
}
