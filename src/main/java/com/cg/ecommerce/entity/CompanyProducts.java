package com.cg.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProducts {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    
	private String productName;
    private double productPrice;
    private String ProductImagePath;
    private String category;
    private String description;   
    
}
