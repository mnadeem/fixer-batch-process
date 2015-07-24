package com.modeln.fxr.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.modeln.fxr.model.Product;

@Repository
public class JdbcProductDao extends JdbcDaoSupport implements ProductDao {

	@Autowired
	public JdbcProductDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public List<Product> find() {
		List<Product> results = getJdbcTemplate().query("SELECT id, name, description, p_type  FROM products", new ProductRowMapper());
		return results;
	}

	@Override
	public List<Product> findOld() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Product product) {
		// TODO Auto-generated method stub
	}
	
	public static class ProductRowMapper implements RowMapper<Product> {

		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product(rs.getLong(1), rs.getString(2), rs.getString(3));
			product.setType(rs.getString(4));
			return product;
		}		
	}
}
