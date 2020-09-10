package com.madingjava.tmall.dao;
 
import com.madingjava.tmall.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order,Integer>{
}
