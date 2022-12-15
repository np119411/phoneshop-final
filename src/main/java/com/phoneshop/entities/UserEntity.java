package com.phoneshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.phoneshop.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;
    
    @Column(name = "avatar", columnDefinition = "TEXT")
    private String avatar;
    
    @Column(name = "full_name", columnDefinition = "VARCHAR(50)")
    private String fullName;
    
    @Column(name = "email", columnDefinition = "VARCHAR(50)")
    private String email;
    
    @Column(name = "phone", columnDefinition = "VARCHAR(20)")
    private String phone;
    
    @Column(name = "is_active")
    private boolean isActive = true;
    
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER; // default user role
}
