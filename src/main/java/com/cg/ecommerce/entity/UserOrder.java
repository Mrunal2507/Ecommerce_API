package com.cg.ecommerce.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrder {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    
	private Date orderDate;
    private Date dispatchDate;
    private int quantity;
    private float cost;
    private String orderStatus;
    
    @ManyToMany
    private Set<CompanyProducts> products;
       
    @ManyToOne
    private Auth auth;
     
}
