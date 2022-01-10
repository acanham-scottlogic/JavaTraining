package com.example.springboot;

public class User {

    private String username;
    private String password;
    private String token;

    public User(){super();}

    public User(String username,String password, String token){
        super();
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String toString(){
        return "User{" + "username= " + username + "  password= " + password + " token= " + token + "}";
    }

    public String getToken(){
        return token;
    }

}
