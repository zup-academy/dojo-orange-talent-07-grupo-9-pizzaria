package br.com.zup.edu.pizzaria.pedidos.novopedido;

import br.com.zup.edu.pizzaria.ingredientes.Ingrediente;
import br.com.zup.edu.pizzaria.ingredientes.IngredienteRepository;
import br.com.zup.edu.pizzaria.pizzas.Pizza;
import br.com.zup.edu.pizzaria.pizzas.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

class NovoPedidoControllerTest {

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    IngredienteRepository ingredienteRepository;

    @Test
    void deveCriarNovoPedido() {

        Ingrediente ingrediente = new Ingrediente("Teste", 2, new BigDecimal("2.0"));
        Ingrediente ingrediente2 = new Ingrediente("Teste2", 2, new BigDecimal("2.0"));
        ingredienteRepository.saveAll(List.of(ingrediente, ingrediente2));

        Pizza pizza1 = new Pizza("Portuguesa", List.of(ingrediente, ingrediente2));
        Pizza pizza2 = new Pizza("Calabresa", List.of(ingrediente, ingrediente2));
        pizzaRepository.saveAll(List.of(pizza1, pizza2));

        EnderecoRequest enderecoRequest = new EnderecoRequest("TST", "123", "TST", "12345-789");
        ItemRequest itemRequest = new ItemRequest();

    }
}