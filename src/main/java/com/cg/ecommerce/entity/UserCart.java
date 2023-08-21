package com.cg.ecommerce.entity;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCart {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;
    private double totalCost;
    private int quantity;
    
    @ManyToMany
    Set<CompanyProducts> productsInCart;
    
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "cart_car_id", referencedColumnName = "emailId")
    private Auth auth;
    
}
