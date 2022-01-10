package com.example.springboot;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class MatcherController {
    Matcher matcher = new Matcher();

    @GetMapping(path="/")
    public String hello(){
        return "hello World";
    }

    @GetMapping(path = "/buyList")
    public ArrayList<Order> getButList(){
        return matcher.getBuyList();
    }

    @GetMapping(path = "/sellList")
    public ArrayList<Order> getSellList(){
        return matcher.getSellList();
    }

    @GetMapping(path = "/tradesList")
    public ArrayList<Trade> getTradesList(){
        return matcher.getTradesList();
    }

    @PostMapping(path="/addOrder", consumes = "application/json", produces="application/json")
    public String addOrder( @Valid
                            @RequestBody
                           Order order)//maybe change this to have account as username using a get method in JWTAuthFilter
    {
        matcher.addOrder(order);
       List<Object> buySellTrade = Arrays.asList(matcher.getBuyList(),matcher.getSellList(),matcher.getTradesList());
       System.out.println(buySellTrade);
        return "congrats, it worked";
    }
}
