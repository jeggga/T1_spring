package org.example.repository;

import org.example.model.Product;
import org.example.model.mapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProductDao(@Autowired NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAllProducts(Long userId) {
        Map<String, Long> map = new HashMap<>();
        map.put("user_id", userId);
        return jdbcTemplate.query("select p.id, p.account_number, p.balance, p.type_of_product, p.user_id " +
                "from product p where p.user_id = :user_id", map, new ProductRowMapper());
    }

    public Product getProductById(Long productId) {
        Map<String, Long> map = new HashMap<>();
        map.put("product_id", productId);
        return jdbcTemplate.queryForObject("select p.id, p.account_number, p.balance, p.type_of_product, p.user_id " +
                "from product p where p.id = :product_id", map, new ProductRowMapper());
    }
}
