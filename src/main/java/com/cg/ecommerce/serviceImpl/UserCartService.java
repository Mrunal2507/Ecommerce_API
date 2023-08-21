package com.cg.ecommerce.serviceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ecommerce.entity.CompanyProducts;
import com.cg.ecommerce.entity.UserCart;
import com.cg.ecommerce.repository.UserCartRepository;

@Service
public class UserCartService {

	@Autowired
    private UserCartRepository cartRepository;

    public boolean addToCart(int cartId,CompanyProducts product){
        Optional<UserCart> currentcart=cartRepository.findById(cartId);
        if(currentcart.isPresent()){
            if(currentcart.get().getProductsInCart().contains(product));
            Set<CompanyProducts> productsList=currentcart.get().getProductsInCart();
            productsList.add(product);
            currentcart.get().setProductsInCart(productsList);
            currentcart.get().setQuantity(currentcart.get().getQuantity()+1);
            currentcart.get().setTotalCost(currentcart.get().getTotalCost()+product.getProductPrice());
            cartRepository.save(currentcart.get());
            return true;
        }
        return false;
    }
    
    public boolean removefromCart(int cartId,CompanyProducts companyProducts){

        Optional<UserCart> currentcart=cartRepository.findById(cartId);
        if(currentcart.isPresent()){
            Set<CompanyProducts> productsList=currentcart.get().getProductsInCart();
            productsList.remove(companyProducts);
            currentcart.get().setQuantity(currentcart.get().getQuantity()-1);
            currentcart.get().setTotalCost(currentcart.get().getTotalCost()-companyProducts.getProductPrice());
            currentcart.get().setProductsInCart(productsList);
            cartRepository.save(currentcart.get());
            return true;
        }
        return false;
    }

    public boolean removeAllFromCart(int cartId){

        Optional<UserCart> currentcart=cartRepository.findById(cartId);
        if(currentcart.isPresent()){
            Set<CompanyProducts> productsList=new HashSet<>();
            currentcart.get().setProductsInCart(productsList);
            currentcart.get().setQuantity(0);
            currentcart.get().setTotalCost(0);
            cartRepository.save(currentcart.get());
            return true;
        }
        return false;
    }
    
    public UserCart getCart(int cartId){
        return cartRepository.findById(cartId).get();
    }
}
