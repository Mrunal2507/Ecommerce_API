package com.cg.ecommerce.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    @Id
    private String emailId;
    private String userFirstName;
    private String mobileNo;
    private String userLastName;
    private String userPassword;
    private boolean isActive;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

    @JsonIgnore
    @OneToMany
    List<UserOrder> userOrders=new ArrayList<>();
    
    @OneToOne(mappedBy = "auth")
    UserCart cart;
    
    @JsonIgnore
    @OneToMany
    List<UserProducts> userProducts=new ArrayList<>();
    
}
