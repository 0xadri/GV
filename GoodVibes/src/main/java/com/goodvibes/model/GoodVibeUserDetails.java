package com.goodvibes.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "USER_DETAILS")
public class GoodVibeUserDetails {

    @Id
    @GeneratedValue
    @Column(name="USER_DETAILS_ID")
    private Integer id;
    
    @Column(name="USERNAME", length = 45, unique=true, nullable=false)
	private String username;

    @Column(name="EMAIL", length = 100, unique=true, nullable=false)
	private String email;

    @Column(name="PASSWORD", length = 100, nullable=false)
	private String password;

    @Transient
	private String passwordConfirmation;
    
    @Column(name="ENABLED", columnDefinition = "TINYINT", nullable=false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean active = true;

    @Column(name="LANG", length = 8)
    private String language = "en";
    
    @Column(name="F_NAME", length = 45)
    private String firstName;
    
    @Column(name="L_NAME", length = 45)
    private String lastName;
    
    @Column(name="GENDER", length = 1)
    private char gender;
    
    @Column(name="LOCATION", length = 45)
    private String location;
    
    public Integer getId() {
		return id;
	}
	@Column(name="DOB")
    private Date dob;
    
    @Column(name="WEBSITES", length = 60)
    private String websites;

    @Column(name="INTERESTS", length =200)
    private String interests;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_DETAILS_ROLE",
               joinColumns={@JoinColumn(name="USER_DETAILS_ID")},
               inverseJoinColumns={@JoinColumn(name="ROLE_ID")})
	private Set<RoleEntity> roles = new HashSet<RoleEntity>(0);
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_DETAILS_IMAGE", 
    		   joinColumns = { @JoinColumn(name = "USER_DETAILS_ID") },
    		   inverseJoinColumns = { @JoinColumn(name = "USER_IMAGE_ID") })
    private Set<UserImage> images = new HashSet<UserImage>(0);
    
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String name) {
		this.username = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getWebsites() {
		return websites;
	}
	public void setWebsites(String websites) {
		this.websites = websites;
	}
	
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	
	public Set<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public Set<UserImage> getImages() {
		return images;
	}
	public void setImages(Set<UserImage> images) {
		this.images = images;
	}
	
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		GoodVibeUserDetails other = (GoodVibeUserDetails) obj;
		
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		
		return true;
	}

}
