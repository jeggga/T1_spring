package org.example.repository;

import org.example.model.User;
import org.example.model.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDao(@Autowired NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("username", name);
        jdbcTemplate.update("INSERT INTO user(username) VALUES (:username)", map);
    }

    public User getUser(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("username", name);
        return jdbcTemplate.queryForObject("select u.id, u.username from user u where u.username = :username", map, new UserRowMapper());
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select u.id, u.username from user u", new UserRowMapper());
    }

    public void deleteUserByName(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("username", name);
        jdbcTemplate.update("delete from user u where u.username = :username", map);
    }
}
