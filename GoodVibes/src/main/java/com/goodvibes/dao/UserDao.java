package com.goodvibes.dao;

import com.goodvibes.model.GoodVibeUserDetails;


public interface UserDao {

	GoodVibeUserDetails findByUsernameOrEmail(String username);
	
	GoodVibeUserDetails getDetailsRolesAndImagesForUser(String username);

}
