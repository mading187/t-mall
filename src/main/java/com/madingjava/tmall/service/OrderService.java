package com.madingjava.tmall.service;

import com.madingjava.tmall.dao.OrderDAO;
import com.madingjava.tmall.pojo.Order;
import com.madingjava.tmall.pojo.OrderItem;
import com.madingjava.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author D
 */
@Service
public class OrderService {
	public static final String waitPay = "waitPay";
	public static final String waitDelivery = "waitDelivery";
	public static final String waitConfirm = "waitConfirm";
	public static final String waitReview = "waitReview";
	public static final String finish = "finish";
	public static final String delete = "delete";	
	
	@Autowired
    OrderDAO orderDAO;
	

	public Page4Navigator<Order> list(int start, int size, int navigatePages) {
    	Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size,sort);
		Page pageFromJPA =orderDAO.findAll(pageable);
		return new Page4Navigator<>(pageFromJPA,navigatePages);
	}

	public void removeOrderFromOrderItem(List<Order> orders) {
		for (Order order : orders) {
			removeOrderFromOrderItem(order);
		}
	}


	/**
	 * 将orderItem中的orfer置空，因为order里面也有orderItem,不置空会造成无限循环
	 * @param order
	 */
	private void removeOrderFromOrderItem(Order order) {
		List<OrderItem> orderItems= order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrder(null);
		}
	}

	public Order get(int oid) {
		return orderDAO.findOne(oid);
	}

	public void update(Order bean) {
		orderDAO.save(bean);
	}




}
