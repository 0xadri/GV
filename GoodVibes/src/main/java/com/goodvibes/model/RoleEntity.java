package com.goodvibes.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table (name = "ROLE")
public class RoleEntity {

    @Id
    @GeneratedValue
    @Column(name="ROLE_ID")
    private Integer id;
    
    @Column(name="ROLE_NAME", length = 45, unique=true, nullable=false)
	private String roleName;

    @ManyToMany(mappedBy="roles")
	private Set<GoodVibeUserDetails> goodVibeUserDetails = new HashSet<GoodVibeUserDetails>();

    
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<GoodVibeUserDetails> getGoodVibeUserDetails() {
		return goodVibeUserDetails;
	}
	public void setGoodVibeUserDetails(Set<GoodVibeUserDetails> goodVibeUserDetails) {
		this.goodVibeUserDetails = goodVibeUserDetails;
	}
	
}
