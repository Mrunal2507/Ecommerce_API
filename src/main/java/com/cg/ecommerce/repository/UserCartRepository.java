package com.cg.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ecommerce.entity.UserCart;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Integer> {
    
}
