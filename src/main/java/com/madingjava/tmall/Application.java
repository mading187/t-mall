package com.madingjava.tmall;

import com.madingjava.tmall.util.PortUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author D
 */
@SpringBootApplication
@EnableCaching
public class Application {
    static{
        PortUtil.checkPort(6379,"redis服务器" , true );
    }
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);    	
    }
}
