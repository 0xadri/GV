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
	private Set<GoodVibeUserDetails> CollectionOfGoodVibeUserDetails = new HashSet<GoodVibeUserDetails>();

    
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<GoodVibeUserDetails> getCollectionOfGoodVibeUserDetails() {
		return CollectionOfGoodVibeUserDetails;
	}
	public void setCollectionOfGoodVibeUserDetails(Set<GoodVibeUserDetails> goodVibeUserDetails) {
		this.CollectionOfGoodVibeUserDetails = goodVibeUserDetails;
	}
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		RoleEntity other = (RoleEntity) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
	
}
