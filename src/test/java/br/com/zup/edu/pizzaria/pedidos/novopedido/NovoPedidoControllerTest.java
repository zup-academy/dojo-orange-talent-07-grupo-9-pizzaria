package br.com.zup.edu.pizzaria.pedidos.novopedido;

import br.com.zup.edu.pizzaria.ingredientes.Ingrediente;
import br.com.zup.edu.pizzaria.ingredientes.IngredienteRepository;
import br.com.zup.edu.pizzaria.pedidos.TipoDeBorda;
import br.com.zup.edu.pizzaria.pizzas.Pizza;
import br.com.zup.edu.pizzaria.pizzas.PizzaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NovoPedidoControllerTest {

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    IngredienteRepository ingredienteRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    void deveCriarNovoPedido() throws Exception {

        Ingrediente ingrediente = new Ingrediente("Teste", 2, new BigDecimal("2.0"));
        Ingrediente ingrediente2 = new Ingrediente("Teste2", 2, new BigDecimal("2.0"));
        ingredienteRepository.saveAll(List.of(ingrediente, ingrediente2));

        Pizza pizza1 = new Pizza("Portuguesa", List.of(ingrediente, ingrediente2));
        Pizza pizza2 = new Pizza("Calabresa", List.of(ingrediente, ingrediente2));
        pizzaRepository.saveAll(List.of(pizza1, pizza2));

        EnderecoRequest enderecoRequest = new EnderecoRequest("TST", "123", "TST", "12345-789");
        ItemRequest item1 = new ItemRequest(pizza1.getId(), TipoDeBorda.TRADICIONAL);
        ItemRequest item2 = new ItemRequest(pizza2.getId(), TipoDeBorda.RECHEADA_CATUPIRY);

        NovoPedidoRequest body = new NovoPedidoRequest(enderecoRequest, List.of(item1, item2));

        MockHttpServletRequestBuilder request = post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(body));

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(redirectedUrlPattern("/api/pedidos/*"));
    }

    @Test
    void naoDeveCriarNovoPedido() throws Exception {

        EnderecoRequest enderecoRequest = new EnderecoRequest("TST", "123", "TST", "12345-789");

        NovoPedidoRequest body = new NovoPedidoRequest(enderecoRequest, new ArrayList<>());

        MockHttpServletRequestBuilder request = post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(body));

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

}