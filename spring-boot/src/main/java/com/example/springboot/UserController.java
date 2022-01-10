package com.example.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class UserController {
    User currentUser;

    @Autowired
    UserRepository userRepository;

    //A new user with a new token is set each time
    @PostMapping("/newUser")
    public User createAccount(@RequestParam("username") String username, @RequestParam("password") String password){
    if (userRepository.doesExist(username)){
        System.out.println("username is already being used");
        currentUser = null;
        return null;
    } else {
        String token = getJWTToken(username);
        User user = new User(username, password, token);
        System.out.println("userCreated");
        userRepository.addUser(user);
        currentUser = user;
        return user;
        }
    }

    @PostMapping("/user")
    public User login(@RequestParam("username") String username, @RequestParam("password") String password){
        User fromDB = userRepository.getUser(username);
        System.out.println("userRepository successful");
        if (fromDB != null){
            System.out.println(fromDB);
        } else {
            System.out.println("username not found");
        }
        return fromDB;
    }


    private String getJWTToken(String username){
        String secretKey = "mySecretKey";//change this to be not constant I guess
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts.builder()
                .setId("AJCJWT")
                .setSubject(username)
                .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();//maybe add setIssuedAt and setExpirationDate
        return "Bearer " + token;
    }

    //matcher controller
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
                                    Order order) //Don't have to put the account on, just enter price, quantity and actionBuy
    {
        order.setAccount(currentUser);
        matcher.addOrder(order);
        List<Object> buySellTrade = Arrays.asList(matcher.getBuyList(),matcher.getSellList(),matcher.getTradesList());
        System.out.println(buySellTrade);
        return "congrats, it worked";
    }

}
