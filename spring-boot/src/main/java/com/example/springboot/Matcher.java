package com.example.springboot;
import java.util.ArrayList;

//matcher needs to do all the matching stuff as with javascript


public class Matcher {
    //introducing variables
    ArrayList<Order> buyList; //high to low
    ArrayList<Order> sellList; //low to high
    ArrayList<Trade> tradesList;
    Trade trade;

    //building the arrays in the constructor
    public Matcher(){
        buyList = new ArrayList<Order>();
        sellList = new ArrayList<Order>();
        tradesList = new ArrayList<Trade>();
    }

    public ArrayList<Order> getBuyList(){
        return buyList;
    }

    public ArrayList<Order> getSellList(){
        return sellList;
    }

    public  ArrayList<Trade> getTradesList(){
        return tradesList;
    }

    public void addOrder(Order order){
        if (order.getActionBuy()){
            for (int i=0; i<sellList.size(); i++){
                if (order.getPrice() >= sellList.get(i).getPrice()){ //match is made
                    trade = new Trade(order, sellList.get(i));
                    tradesList.add(0,trade);
                    order.setQuantity(order.getQuantity() - trade.getQuantity());
                    sellList.get(i).setQuantity(sellList.get(i).getQuantity() - trade.getQuantity());
                    if (sellList.get(i).getQuantity() == 0){
                        sellList.remove(i);
                        i--;
                    }
                }
                if (order.getQuantity() == 0){
                    break;
                }
            }
            if (order.getQuantity() != 0){
                int x = -1;
                for (int j=0; j<buyList.size(); j++){  //find j position in buyList
                    if (buyList.get(j).getPrice() <  order.getPrice()){
                        x = j;
                        break;
                    }
                }
                if(x==-1){
                    buyList.add(order);
                } else {
                    buyList.add(x, order);
                }
            }
        } else { //now do a sell order
            for (int i=0;  i<buyList.size(); i++){
                if (order.getPrice() <= buyList.get(i).getPrice()){
                    trade = new Trade(order,buyList.get(i));
                    tradesList.add(0,trade);
                    order.setQuantity(order.getQuantity() - trade.getQuantity());
                    buyList.get(i).setQuantity(buyList.get(i).getQuantity() - trade.getQuantity());
                    if (buyList.get(i).getQuantity() == 0){
                        buyList.remove((i));
                        i--;
                    }
                }
                if(order.getQuantity() == 0) {
                    break;
                }
            }
            if(order.getQuantity() != 0){
                int x=-1;
                for (int j=0;j<sellList.size();j++){
                    if (sellList.get(j).getPrice() > order.getPrice()) {
                        x = j;
                        break;
                    }
                }
                if(x==-1){
                    sellList.add(order);
                } else{
                    sellList.add(x,order);
                }

            }
        }
    }

}
