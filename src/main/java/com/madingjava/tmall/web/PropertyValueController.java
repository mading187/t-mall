package com.madingjava.tmall.web;
import com.madingjava.tmall.pojo.Product;
import com.madingjava.tmall.pojo.PropertyValue;
import com.madingjava.tmall.service.ProductService;
import com.madingjava.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
 
@RestController
public class PropertyValueController {
	@Autowired PropertyValueService propertyValueService;
	@Autowired ProductService productService;

    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") int pid) throws Exception {
    	Product product = productService.get(pid);
    	propertyValueService.init(product);
    	List<PropertyValue> propertyValues = propertyValueService.list(product);
    	return propertyValues;
    }
        
    @PutMapping("/propertyValues")
    public Object update(@RequestBody PropertyValue bean) throws Exception {
    	propertyValueService.update(bean);
        return bean;
    }
    
  
}

