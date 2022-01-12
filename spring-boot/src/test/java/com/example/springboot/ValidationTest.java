package com.example.springboot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ValidationTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        System.out.println("testing the validation");
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    public void tearDown(){
        validatorFactory.close();
    }

    @Test
    void testingTestClass(){
        System.out.println("Succcess");
    }

    @Test
    void noViolationOrder(){
        Order order = new Order("account1",3.51,5,false);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        assertTrue(violations.isEmpty());
    }

    /*  Allowed since updated to link user and account
    @Test
    void emptyAccountOrder(){
        Order order = new Order("",3.51,5,false);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Order> violation = violations.iterator().next();
        assertEquals("account cannot be empty", violation.getMessage());
        assertEquals("account", violation.getPropertyPath().toString());
        assertEquals("", violation.getInvalidValue());
    }//Some stuff that validator factory can do that I found on the internet
*/
    @Test
    void negPriceOrder(){
        Order order = new Order("account",-3.51,5,false);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Order> violation = violations.iterator().next();
        assertEquals("Price must be positive and greater than 0", violation.getMessage());
        assertEquals("price", violation.getPropertyPath().toString());
        assertEquals(-3.51, violation.getInvalidValue());
    }

    @Test
    void emptyQuantityOrder(){
        Order order = new Order("account",3.51,0,false);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Order> violation = violations.iterator().next();
        assertEquals("must trade a quantity higher than or equal to 1", violation.getMessage());
        assertEquals("quantity", violation.getPropertyPath().toString());
        assertEquals(0, violation.getInvalidValue());
    }

    @Test
    void negQuantityOrder(){
        Order order = new Order("account",3.51,-3,false);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        assertEquals(violations.size(), 1);
        ConstraintViolation<Order> violation = violations.iterator().next();
        assertEquals("must trade a quantity higher than or equal to 1", violation.getMessage());
        assertEquals("quantity", violation.getPropertyPath().toString());
        assertEquals(-3, violation.getInvalidValue());
    }

    //Didn't bother testing null stuff ah well
}
