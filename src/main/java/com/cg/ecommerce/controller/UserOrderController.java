package com.cg.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ecommerce.entity.UserOrder;
import com.cg.ecommerce.serviceImpl.UserOrderService;

@RestController
@RequestMapping("/userOrder")
@CrossOrigin(origins="http://localhost:4200")
public class UserOrderController {
	@Autowired
    private UserOrderService orderService;
    
	@PostMapping("/addOrder/{emaildId}")
	public ResponseEntity<Boolean> addOrder(
			@RequestBody UserOrder order,
            @PathVariable String emaildId
			)throws Exception{
		return new ResponseEntity<Boolean>(orderService.addOrders(order,emaildId), HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<UserOrder>> getAllOrders(
			)throws Exception{
		return new ResponseEntity<List<UserOrder>>(orderService.getAllOrders(), HttpStatus.OK);
	}
	
	@GetMapping("/getAllOrdersByEmailId/{emaildId}")
	public ResponseEntity<List<UserOrder>> getOrdersByEmailId(
		@PathVariable String emaildId
			)throws Exception{
		return new ResponseEntity<List<UserOrder>>(orderService.getOrdersByEmailId(emaildId), HttpStatus.OK);
	}
}
