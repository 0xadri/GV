package com.goodvibes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.goodvibes.dao.UserDao;
import com.goodvibes.model.GoodVibeUserDetails;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public GoodVibeUserDetails getUser(String username) throws UsernameNotFoundException {
		
		GoodVibeUserDetails user = userDao.getDetailsRolesAndImagesForUser(username);
		
		return user;
	}

	@Override
	public void registerUser(GoodVibeUserDetails user) {
		userDao.registerUser(user);
	}

	@Override
	public boolean doesUsernameAlreadyExist(String username) {
		
		GoodVibeUserDetails user = null;
		try{
			user = userDao.findByUsername(username);
		}
		catch(UsernameNotFoundException e){	}
		
		return user != null;
	}

	@Override
	public boolean doesEmailAlreadyExist(String email) {
		
		GoodVibeUserDetails user = null;
		try{
			user = userDao.findByEmail(email);
		}
		catch(UsernameNotFoundException e){	}
		
		return user != null;
	}

	
}
