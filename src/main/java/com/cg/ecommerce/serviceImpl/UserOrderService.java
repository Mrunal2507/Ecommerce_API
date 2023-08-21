package com.cg.ecommerce.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ecommerce.entity.UserOrder;
import com.cg.ecommerce.repository.AuthRepository;
import com.cg.ecommerce.repository.UserOrderRepository;

@Service
public class UserOrderService {

	@Autowired
	private UserOrderRepository orderRepository;
	
	@Autowired
	private AuthRepository authRepository;

	public boolean addOrders(UserOrder order, String userEmailId) {
		order.setAuth(authRepository.findById(userEmailId).get());
		orderRepository.save(order);
		return true;
	}
	
//	public boolean addOrders(UserOrder order, String userEmailId) {
//	    Auth auth = authRepository.findById(userEmailId).orElse(null);
//	    if (auth == null) {
//	        return false; // Handle the case when the user email ID doesn't exist
//	    }
//
//	    order.setAuth(auth);
//	    Set<CompanyProducts> productsInCart = auth.getProductsInCart();
//	    order.setProducts(new HashSet<>(productsInCart));
//
//	    orderRepository.save(order);
//	    return true;
//	}
	
	public List<UserOrder> getAllOrders() {
        return orderRepository.findAll();
    }


    public List<UserOrder> getOrdersByEmailId(String emailId) {
        return orderRepository.findByAuthEmailId(emailId);
    }

}
