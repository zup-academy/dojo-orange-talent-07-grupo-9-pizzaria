package br.com.zup.edu.pizzaria.pizzas.cadastropizza;

import br.com.zup.edu.pizzaria.ingredientes.Ingrediente;
import br.com.zup.edu.pizzaria.ingredientes.IngredienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NovaPizzaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private IngredienteRepository repository;

    @Test
    void deveCadastrarUmaNovaPizza() throws Exception {
        Ingrediente ingrediente = new Ingrediente("Teste", 2, new BigDecimal("2.0"));
        Ingrediente ingrediente2 = new Ingrediente("Teste2", 2, new BigDecimal("2.0"));
        repository.saveAll(List.of(ingrediente, ingrediente2));

        NovaPizzaRequest body = new NovaPizzaRequest("Peperone", List.of(ingrediente.getId(), ingrediente2.getId()));

        MockHttpServletRequestBuilder request = post("/api/pizzas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(body));

        mvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    void deveDarErroAoCadastrarUmaNovaPizza() throws Exception {
        Ingrediente ingrediente = new Ingrediente("Teste", 2, new BigDecimal("2.0"));
        Ingrediente ingrediente2 = new Ingrediente("Teste2", 2, new BigDecimal("2.0"));
        repository.saveAll(List.of(ingrediente, ingrediente2));

        NovaPizzaRequest body = new NovaPizzaRequest("", List.of(ingrediente.getId(), ingrediente2.getId()));

        MockHttpServletRequestBuilder request = post("/api/pizzas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(body));

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }
}