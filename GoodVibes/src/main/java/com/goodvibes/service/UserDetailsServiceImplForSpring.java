package com.goodvibes.service;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodvibes.dao.UserDao;
import com.goodvibes.model.GoodVibeUserDetails;
import com.goodvibes.model.RoleEntity;


// THIS SERVICE MUST NOT BE USED BY ANYTHING ELSE THAN SPRING !

@Service("userDetailsService")
public class UserDetailsServiceImplForSpring implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	// It is impossible to display any message using sysout
	// But you can use the eclipse debug mode here
	// Spring calls loadUserByUsername() method by default so that you can get the user credentials & all
	// Here we leave the user enter either a username or email to identify him/herself
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String usernameOrEmail)
			throws UsernameNotFoundException, DataAccessException {

		// This is where we plug in our DAO layer to access credentials & authorities (could be plugged to a web service instead) 
		// When findByUsername() throws UsernameNotFoundException
		// Spring will display "Bad credentials" as the Reason for not being able to log in
		GoodVibeUserDetails userDetails  = userDao.findByUsernameOrEmail(usernameOrEmail);
		
		Hibernate.initialize(userDetails.getRoles());
		
		// Building the user for Spring expected format
		User user = buildUserForSpring(userDetails);
		
		// The credential checking is done by Spring.
		// We only return the the new user with its expected credentials & assigned authorities		
		return user;
	}
	
	private User buildUserForSpring(GoodVibeUserDetails userDetails) {

		String username = userDetails.getUsername();
		String password = userDetails.getPassword();
		boolean enabled = userDetails.isActive();
		boolean accountNonExpired = userDetails.isActive();
		boolean credentialsNonExpired = userDetails.isActive();
		boolean accountNonLocked = userDetails.isActive();

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (RoleEntity role : userDetails.getRoles()) {
			authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
		}
		
		return new User(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
	}

}