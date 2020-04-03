package com.authentification.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "App_User")
public class AppUser {
	
	@Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
 
    private String userName;
    
    @Column(length = 128)
    private String password;
    
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> DatasRoles = new ArrayList<>(); 
 
//    @Column(name = "Enabled", length = 1, nullable = false)
//    private boolean enabled;
    
    

}
