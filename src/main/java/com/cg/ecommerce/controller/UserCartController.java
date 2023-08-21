package com.cg.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ecommerce.entity.CompanyProducts;
import com.cg.ecommerce.entity.UserCart;
import com.cg.ecommerce.serviceImpl.UserCartService;

@RestController
@RequestMapping("/userCart")
@CrossOrigin(origins = "http://localhost:4200")
public class UserCartController {

	@Autowired
	private UserCartService cartService;

	@PutMapping("/addToCart/{cartId}")
	public ResponseEntity<Boolean> addProductsToCart(@RequestBody CompanyProducts companyProducts,
			@PathVariable int cartId) throws Exception {
		return new ResponseEntity<Boolean>(cartService.addToCart(cartId, companyProducts), HttpStatus.OK);
	}

	@GetMapping("/getCart/{cartId}")
	public ResponseEntity<UserCart> getCartById(@PathVariable int cartId) throws Exception {
		return new ResponseEntity<UserCart>(cartService.getCart(cartId), HttpStatus.OK);
	}

	@DeleteMapping("/removeFromCart/{cartId}")
	public ResponseEntity<Boolean> removeFromCart(@PathVariable int cartId,
			@RequestBody CompanyProducts companyProducts) throws Exception {
		return new ResponseEntity<Boolean>(cartService.removefromCart(cartId, companyProducts), HttpStatus.OK);
	}

	@DeleteMapping("/removeAllFromCart/{cartId}")
	public ResponseEntity<Boolean> removeAllFromCart(@PathVariable int cartId) throws Exception {
		return new ResponseEntity<Boolean>(cartService.removeAllFromCart(cartId), HttpStatus.OK);
	}
}
