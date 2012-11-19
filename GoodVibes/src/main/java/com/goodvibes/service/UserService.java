package com.goodvibes.service;

import com.goodvibes.model.GoodVibeUserDetails;


public interface UserService {

	public GoodVibeUserDetails getUser(String username);
	
	public void registerUser(GoodVibeUserDetails user);

	boolean doesUsernameAlreadyExist(String username);

	boolean doesEmailAlreadyExist(String email);
	
	// TODO necessary??
	//void saveRoleForUser(String username, RoleEntity role);
	
	// TODO necessary??
	//void saveImageForUser(String username, UserImage image);
	
}
