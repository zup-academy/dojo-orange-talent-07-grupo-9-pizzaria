package br.com.zup.edu.pizzaria.pizzas.cadastropizza;

import br.com.zup.edu.pizzaria.ingredientes.Ingrediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NovaPizzaControllerTest {

    @Autowired
    private MockMvc mvc;

    void deveCadastrarUmaNovaPizza(){
        Ingrediente ingrediente = new Ingrediente("Sei la", 2, new BigDecimal("2.0"));
        NovaPizzaRequest body = new NovaPizzaRequest("Peperone", List.of(1L, 2L));
    }

}