package br.com.zup.edu.pizzaria.pizzas;

import br.com.zup.edu.pizzaria.ingredientes.Ingrediente;
import br.com.zup.edu.pizzaria.pizzas.cadastropizza.NovaPizzaRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PizzaTest {

    @Test
    void deveCalcularPrecoCorretamente() {
        BigDecimal precoMassa = new BigDecimal("15.0");
        BigDecimal precoMaoDeObra = new BigDecimal("5.0");
        Ingrediente ingrediente = new Ingrediente("Calabresa", 2, new BigDecimal("7.0"));
        Ingrediente ingrediente2 = new Ingrediente("Muçarela", 2, new BigDecimal("15.5"));
        Pizza pizza = new Pizza("Calabresa",List.of(ingrediente, ingrediente2));

        BigDecimal precoIngredientes = ingrediente.getPreco().add(ingrediente2.getPreco());

        BigDecimal precoEsperado = precoIngredientes.add(precoMassa.add(precoMaoDeObra));

        Assertions.assertEquals(precoEsperado, pizza.getPreco() );
    }
}