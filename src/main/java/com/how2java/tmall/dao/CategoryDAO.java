package com.how2java.tmall.dao;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.tmall.pojo.Category;

public interface CategoryDAO extends JpaRepository<Category,Integer>{

}
