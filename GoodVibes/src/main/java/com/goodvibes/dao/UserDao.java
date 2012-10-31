package com.goodvibes.dao;

import com.goodvibes.model.GoodVibeUserDetails;


public interface UserDao {

	GoodVibeUserDetails findByUsernameOrEmail(String username);
	
	GoodVibeUserDetails getDetailsRolesAndImagesForUser(String username);
	
	GoodVibeUserDetails registerUser(GoodVibeUserDetails user);

	// TODO
	//void updateDetailsForUser(GoodVibeUserDetails user);
	
	// TODO
	//void saveRoleForUser(String username, RoleEntity role);
	
	// TODO
	//void saveImageForUser(String username, UserImage image);
	
	// TODO
	//void updateDetailsRolesAndImagesForUser(GoodVibeUserDetails user);

}
