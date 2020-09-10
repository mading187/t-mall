package com.madingjava.tmall.dao;
 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madingjava.tmall.pojo.Product;
import com.madingjava.tmall.pojo.Property;
import com.madingjava.tmall.pojo.PropertyValue;

public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer>{

	List<PropertyValue> findByProductOrderByIdDesc(Product product);
	PropertyValue getByPropertyAndProduct(Property property, Product product);

	
	
	
}
