package br.com.zup.edu.pizzaria.ingredientes.cadastrodeingredientes;

import br.com.zup.edu.pizzaria.ingredientes.Ingrediente;
import br.com.zup.edu.pizzaria.ingredientes.cadastrodeingredientes.NovoIngredienteRequest;
import br.com.zup.edu.pizzaria.shared.validators.UniqueValue;
import br.com.zup.edu.pizzaria.shared.validators.UniqueValueValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dom4j.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@Transactional
class UniqueValueValidatorTest {

    @PersistenceContext
    EntityManager manager;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    UniqueValueValidator uniqueValueValidator;

    @BeforeEach
    void setup() {
        this.uniqueValueValidator = new UniqueValueValidator(manager);
    }

    @Test
    void isValid() {
        NovoIngredienteRequest ingrediente1 = new NovoIngredienteRequest("Queijo muçarela", new BigDecimal("2.0"), 200);
        Ingrediente ingrediente = ingrediente1.paraIngrediente();
        manager.persist(ingrediente);

        UniqueValueTest uniqueValueTest = new UniqueValueTest();

        uniqueValueValidator.initialize(uniqueValueTest);
        uniqueValueValidator.isValid("Queijo muçarela", constraintValidatorContext);
    }
}

@DataJpaTest
class UniqueValueTest implements UniqueValue {

    @Override
    public String message() {
        return "O nome não deve ser nulo.";
    }

    @Override
    public Class<?>[] groups() {
        return new Class[0];
    }

    @Override
    public Class<? extends Payload>[] payload() {
        return new Class[0];
    }

    @Override
    public Class<?> domainClass() {
        return Ingrediente.class;
    }

    @Override
    public String domainAtribute() {
        return "nome";
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}