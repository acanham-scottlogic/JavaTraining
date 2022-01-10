package com.example.springboot;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Order {

    private String account;

    @NotNull(message = "price cannot be a null value")
    @DecimalMin(value = "0.01", message = "Price must be positive and greater than 0")
    private final double price;

    @NotNull(message = "quantity cannot be a null value")
    @Min(value = 1, message = "must trade a quantity higher than or equal to 1")
    private int quantity;

    @NotNull(message = "action cannot be null")
    private final boolean actionBuy;  //as the action can be only one of two things, make this true if the action is buy, and false otherwise

    //constructor method
    public Order(String account,double price, int quantity, boolean actionBuy){
        this.account = account;
        this.price = price;
        this.quantity = quantity;
        this.actionBuy = actionBuy;

    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setAccount(User user){ account = user.getUsername(); }

    public String getAccount(){
        return account;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public boolean getActionBuy(){
        return actionBuy;
    }

    @Override
    public String toString(){
        return "Order{" + "account= " + account + "  price= " + price + " quantity= " + quantity + " actionBuy= " + actionBuy + "}";
    }

}
