package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addUser(User user){
        return jdbcTemplate.update("insert into users_tbl (username, password) values (?,?)", user.getUsername(), user.getPassword());
    }


    public User getUser(String username){
        System.out.println("trying to enter database asdgasdguihapdfisughapdfh");
        try {
            String sql = "select * from users_tbl where username=";
            LinkedCaseInsensitiveMap found = (LinkedCaseInsensitiveMap) jdbcTemplate.queryForList(sql + "'" + username + "'").get(0);
            System.out.println(found);

            User user = new User((String) found.get("username"), (String) found.get("password"));

            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
