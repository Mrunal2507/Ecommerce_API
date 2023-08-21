package com.cg.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ecommerce.entity.CompanyProducts;
@Repository
public interface CompanyProductsRepository extends JpaRepository<CompanyProducts,Integer>{
	
	public List<CompanyProducts> findByCategory(String category);
}
