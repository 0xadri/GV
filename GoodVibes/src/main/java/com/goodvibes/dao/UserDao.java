package com.goodvibes.dao;

import com.goodvibes.model.GoodVibeUserDetails;


public interface UserDao {

	GoodVibeUserDetails registerUser(GoodVibeUserDetails user);

	GoodVibeUserDetails findByUsernameOrEmail(String username);

	GoodVibeUserDetails getDetailsRolesAndImagesForUser(String username);
	
	// TODO: GoodVibeUserDetails updateUser(GoodVibeUserDetails user);
	// check it cascades the changes to images, roles, etc
	
	// TODO: GoodVibeUserDetails deleteUser(GoodVibeUserDetails user);
	// Make user inactive only?
	
}
