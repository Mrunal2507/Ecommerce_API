package com.cg.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ecommerce.entity.Role;
import com.cg.ecommerce.serviceImpl.RoleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@PostMapping({ "/createNewRole" })
	public Role createNewRole(@RequestBody Role role) {
		return roleService.createNewRole(role);
	}
}
