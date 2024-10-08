package org.example.model.mapper;

import org.example.model.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRowMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users users = new Users();
        users.setId(rs.getLong("id"));
        users.setUsername(rs.getString("username"));

        return users;
    }
}
