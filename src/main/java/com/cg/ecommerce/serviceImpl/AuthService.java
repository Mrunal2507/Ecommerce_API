package com.cg.ecommerce.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ecommerce.entity.Auth;
import com.cg.ecommerce.entity.Role;
import com.cg.ecommerce.entity.UserCart;
import com.cg.ecommerce.repository.AuthRepository;
import com.cg.ecommerce.repository.RoleRepository;
import com.cg.ecommerce.repository.UserCartRepository;
@Service
public class AuthService {
    @Autowired
    private AuthRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserCartRepository cartRepository;
    
    public void initRoleAndUser() {

    	Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepository.save(userRole);

        Auth adminUser = new Auth();
        adminUser.setEmailId("admin@gmail.com");
        adminUser.setUserPassword("Admin@1234");
        adminUser.setUserFirstName("Admin");
        adminUser.setUserLastName("Admin");
        adminUser.setMobileNo("7544366332");
        adminUser.setActive(true);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);
    }

    public Auth registerNewUser(Auth user) {
        Role role = roleRepository.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setActive(true);
        user.setRole(userRoles);
        Auth auth= userRepository.save(user);
        UserCart cart = new UserCart();
        cart.setAuth(auth);
        cartRepository.save(cart);
        auth.setUserPassword(null);
        return auth;
    }

    public Auth authenticateuser(Auth auth){
        if(userRepository.existsById(auth.getEmailId())){
            Auth existauth=userRepository.findById(auth.getEmailId()).get();
            if(existauth.isActive() && existauth.getUserPassword().equals(auth.getUserPassword())){
                existauth.setUserPassword(null);
                return existauth;
            }else return null;
        }
        else return null;
    }
    
    public Optional<Auth> getUserByEmailId(String emailId) {
        Optional<Auth> authOptional = userRepository.findById(emailId);
        if (authOptional.isPresent()) {
            return authOptional;
        }
		return authOptional;
    }
    
    public List<Auth> getAllUsers(){
    	return userRepository.findAll();
    }
    
    public void deactivateUser(String emailId) {
        Auth auth = userRepository.findById(emailId).get();
        if (auth != null) {
            auth.setActive(false);
            userRepository.save(auth);
        }
    }
    
    public void activateUser(String emailId) {
        Auth auth = userRepository.findById(emailId).get();
        if (auth != null) {
            auth.setActive(true);
            userRepository.save(auth);
        }
    }
    
    public boolean getUserStatusByEmail(String emailId) {
        Optional<Auth> authOptional = userRepository.findById(emailId);
        if (authOptional.isPresent()) {
            Auth auth = authOptional.get();
            return auth.isActive();
        } else {
            return false;
        }
    }
    
    public Auth updateAuthByEmail(String emailId, Auth updatedAuth) {
        Optional<Auth> existingAuthOptional = userRepository.findById(emailId);
        if (existingAuthOptional.isEmpty()) {
            // Handle the case when the user with the provided emailId doesn't exist.
            return null;
        }

        Auth existingAuth = existingAuthOptional.get();

        // Update the fields of existingAuth with the fields of updatedAuth
        BeanUtils.copyProperties(updatedAuth, existingAuth, "emailId");

        // Save the updatedAuth entity
        return userRepository.save(existingAuth);
    }
}
