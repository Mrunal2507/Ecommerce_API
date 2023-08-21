package com.cg.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ecommerce.entity.Auth;
import com.cg.ecommerce.serviceImpl.AuthService;

import jakarta.annotation.PostConstruct;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostConstruct
	public void initRoleAndUser() {
		authService.initRoleAndUser();
	}

	@PostMapping({ "/registerNewUser" })
	public Auth registerNewUser(@RequestBody Auth user) {
		return authService.registerNewUser(user);
	}

	@PostMapping({ "/authenticateuser" })
	public Auth authenticateuser(@RequestBody Auth user) {
		return authService.authenticateuser(user);
	}
	
	@GetMapping("/user/{emailId}")
	public Auth getUserByEmailId(@PathVariable String emailId) {
        Optional<Auth> authOptional = authService.getUserByEmailId(emailId);
        return authOptional.orElseThrow();
    }
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<Auth>> getAllUsers() throws Exception{
        return new ResponseEntity<List<Auth>>(authService.getAllUsers(),HttpStatus.OK);
    }
	
	@PostMapping("/{emailId}/deactivate")
    public void deactivateUser(@PathVariable String emailId) {
        authService.deactivateUser(emailId);
    }

    @PostMapping("/{emailId}/activate")
    public void activateUser(@PathVariable String emailId) {
        authService.activateUser(emailId);
    }
    
    @GetMapping("/users/{emailId}/status")
    public boolean getUserStatusByEmail(@PathVariable String emailId) {
        return authService.getUserStatusByEmail(emailId);
    }
    
    @PutMapping("/{emailId}")
    public ResponseEntity<Auth> updateAuthDataByEmail(
            @PathVariable String emailId,
            @RequestBody Auth updatedAuth
    ) {
        Auth updatedEntity = authService.updateAuthByEmail(emailId, updatedAuth);
        if (updatedEntity == null) {
            // Handle the case when the user with the provided emailId doesn't exist.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // The entity has been updated successfully.
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }
}
