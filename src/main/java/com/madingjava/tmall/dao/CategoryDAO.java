package com.madingjava.tmall.dao;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.madingjava.tmall.pojo.Category;

public interface CategoryDAO extends JpaRepository<Category,Integer>{

}
