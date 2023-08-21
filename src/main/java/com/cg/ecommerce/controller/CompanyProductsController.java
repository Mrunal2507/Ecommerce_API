package com.cg.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ecommerce.entity.CompanyProducts;
import com.cg.ecommerce.serviceImpl.CompanyProductsService;

@RestController
@RequestMapping("/CompanyProducts")
@CrossOrigin(origins="http://localhost:4200")
public class CompanyProductsController {

	@Autowired
    private CompanyProductsService companyProductsService;
    
	@PostMapping("/addCompanyProducts")
	public ResponseEntity<Boolean> addCompanyProducts(
			@RequestBody CompanyProducts companyProducts
			)throws Exception{
		return new ResponseEntity<Boolean>(companyProductsService.addCompanyProducts(companyProducts), HttpStatus.OK);
	}
	
    @GetMapping("/getAllCompanyProducts")
    public ResponseEntity<List<CompanyProducts>> getAllCompanyProducts() throws Exception{
        return new ResponseEntity<List<CompanyProducts>>(companyProductsService.getAllCompanyProducts(),HttpStatus.OK);
    }
    
    @GetMapping("/getAllCompanyProductsByCategory/{category}")
    public ResponseEntity<List<CompanyProducts>> getAllCompanyProductsByCategory(
        @PathVariable String category
    ) throws Exception{
        return new ResponseEntity<List<CompanyProducts>>(companyProductsService.getAllProductsByCategory(category),HttpStatus.OK);
    }
    
}
