package com.cg.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.ecommerce.entity.UserProducts;
import com.cg.ecommerce.serviceImpl.UserProductsService;

@RestController
@RequestMapping("/userProduct")
@CrossOrigin(origins="*")
public class UserProductCotroller {
	
	@Autowired
    private UserProductsService userProductsService;

	@PostMapping("/adduseproducts")
	public ResponseEntity<Boolean> addUserProducts(
            @RequestParam("emailId") String emailId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("userProducts") String userProducts
			)throws Exception{
		return new ResponseEntity<Boolean>(userProductsService.addUserProducts(userProducts,emailId,file) , HttpStatus.OK);
	}
    
    @GetMapping("/getAllUserProducts")
    public ResponseEntity<List<UserProducts>> getAllUserProducts() throws Exception{
        return new ResponseEntity<List<UserProducts>>(userProductsService.getAllUserProducts(),HttpStatus.OK);
    }

    @GetMapping("/getAllUserProductsByEmailId/{emailId}")
    public ResponseEntity<List<UserProducts>> getAllUserProductsByEmailId(
        @PathVariable String emailId
    ) throws Exception{
        return new ResponseEntity<List<UserProducts>>(userProductsService.getAllProductsByEmailId(emailId),HttpStatus.OK);
    }

    @PutMapping("/changeApprovalStatus/{productId}/{status}")
    public ResponseEntity<Boolean> changeApprovalStatus(
        @PathVariable int productId,
        @PathVariable String status
    ) throws Exception{
        return new ResponseEntity<Boolean>(userProductsService.changeApprovalStatusByUserProductId(productId, status),HttpStatus.OK);
    }
    
    @GetMapping("/getAllUserProductsByCategory/{category}")
    public ResponseEntity<List<UserProducts>> getAllUserProductsByCategory(
        @PathVariable String category
    ) throws Exception{
        return new ResponseEntity<List<UserProducts>>(userProductsService.getAllProductsByCategory(category),HttpStatus.OK);
    }
    
    @GetMapping("/getAllUserProductsByStatus/{approvalStatus}")
    public ResponseEntity<List<UserProducts>> getAllUserProductsByStatus(
        @PathVariable String approvalStatus
    ) throws Exception{
        return new ResponseEntity<List<UserProducts>>(userProductsService.getAllProductsByStatus(approvalStatus),HttpStatus.OK);
    }
}
