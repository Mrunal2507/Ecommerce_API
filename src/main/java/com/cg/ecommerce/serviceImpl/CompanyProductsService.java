package com.cg.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ecommerce.entity.CompanyProducts;
import com.cg.ecommerce.repository.CompanyProductsRepository;

@Service
public class CompanyProductsService {
	
	@Autowired
    private CompanyProductsRepository companyProductsRepository ;
    
	public boolean addCompanyProducts(CompanyProducts companyProducts){
        if(companyProducts!=null){
            if(companyProductsRepository.save(companyProducts)!=null)return true;
        }
        return false;
    }
    
    public List<CompanyProducts> getAllCompanyProducts(){
        return companyProductsRepository.findAll();
    }
    
    public List<CompanyProducts> getAllProductsByCategory(String category){
    	return companyProductsRepository.findByCategory(category);
    }
    
}
