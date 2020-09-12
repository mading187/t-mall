package com.madingjava.tmall.dao;
 
import com.madingjava.tmall.pojo.Order;
import com.madingjava.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order,Integer>{
    public List<Order> findByUserAndStatusNotOrderByIdDesc(User user, String status);
}
