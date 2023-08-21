package com.cg.ecommerce.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProducts {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userProductId;
    
	private String UserProductName;
    private String UserProductImage;
    private float UserProductPrice;
    private String category;
    private String description;
    private Date requestDate;
    private String approvalStatus;
    
    @ManyToOne
    private Auth auth;
    
}
