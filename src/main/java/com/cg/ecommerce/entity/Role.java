package com.cg.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private String roleName;
    private String roleDescription;

//	public Role() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public String getRoleName() {
//		return roleName;
//	}
//	
//	public void setRoleName(String roleName) {
//		this.roleName = roleName;
//	}
//
//	public String getRoleDescription() {
//		return roleDescription;
//	}
//
//	public void setRoleDescription(String roleDescription) {
//		this.roleDescription = roleDescription;
//	}
    
    
    
}
