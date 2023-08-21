package com.cg.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ecommerce.entity.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String>{

	Auth save(Optional<Auth> existingAuth);

}
