package com.cg.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ecommerce.entity.UserProducts;

@Repository
public interface UserProductsRepository extends JpaRepository<UserProducts,Integer>{
	
	public List<UserProducts> findByAuthEmailId(String emailId);
	public List<UserProducts> findByCategory(String category);
	public List<UserProducts> findAllByApprovalStatus(String approvalStatus);
}
