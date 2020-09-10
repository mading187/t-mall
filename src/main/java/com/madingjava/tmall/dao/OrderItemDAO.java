package com.madingjava.tmall.dao;

import com.madingjava.tmall.pojo.Order;
import com.madingjava.tmall.pojo.OrderItem;
import com.madingjava.tmall.pojo.Product;
import com.madingjava.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDAO extends JpaRepository<OrderItem,Integer>{
	List<OrderItem> findByOrderOrderByIdDesc(Order order);
	List<OrderItem> findByProduct(Product product);
	List<OrderItem> findByUserAndOrderIsNull(User user);
}
