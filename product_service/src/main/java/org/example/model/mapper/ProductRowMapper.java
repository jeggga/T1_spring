package org.example.model.mapper;

import org.example.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setAccountNumber(rs.getString("account_number"));
        product.setBalance(Objects.isNull(rs.getString("balance")) ? null : BigDecimal.valueOf(Double.parseDouble(rs.getString("balance"))));
        product.setTypeOfProduct(rs.getString("type_of_product"));
        product.setUserId(rs.getLong("user_id"));
        return product;
    }
}
