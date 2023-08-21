package com.cg.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ecommerce.entity.UserOrder;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder,Integer>{

	public List<UserOrder>findByAuthEmailId(String authEmailId);
}
