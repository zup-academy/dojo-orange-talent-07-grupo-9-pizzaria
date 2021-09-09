package br.com.zup.edu.pizzaria.pizzas.cadastropizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NovaPizzaControllerTest {

    @Autowired
    private MockMvc mvc;

    void deveCadastrarUmaNovaPizza(){
//        NovaPizzaRequest body = new NovaPizzaRequest()
    }

}