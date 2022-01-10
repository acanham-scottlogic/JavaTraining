package com.example.springboot;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static java.lang.Math.min;

public class Trade {

    @NotNull(message = "price cannot be a null value")
    @DecimalMin(value = "0.01", message = "Price must be positive and greater than 0")
    private double price;

    @NotNull(message = "quantity cannot be a null value")
    @Min(value = 1, message = "must trade a quantity higher than or equal to 1")
    private int quantity;

    public Trade (Order order, Order oldOrder){
        this.price = oldOrder.getPrice();
        this.quantity = min(order.getQuantity() , oldOrder.getQuantity());
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }
}
