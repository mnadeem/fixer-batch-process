package com.modeln.fxr.repository;

import java.util.List;

import com.modeln.fxr.model.Product;

public interface ProductDao {
	
	List<Product> find();
	List<Product> findOld();
	void save(Product product);
}
