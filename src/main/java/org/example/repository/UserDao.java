package org.example.repository;

import org.example.model.Users;
import org.example.model.mapper.UsersRowMapper;
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
        jdbcTemplate.update("INSERT INTO users(username) VALUES (:username)", map);
    }

    public Users getUser(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("username", name);
        return jdbcTemplate.queryForObject("select u.id, u.username from users u where u.username = :username", map, new UsersRowMapper());
    }

    public List<Users> getAllUsers() {
        return jdbcTemplate.query("select u.id, u.username from users u", new UsersRowMapper());
    }

    public void deleteUserByName(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("username", name);
        jdbcTemplate.update("delete from users u where u.username = :username", map);
    }
}
