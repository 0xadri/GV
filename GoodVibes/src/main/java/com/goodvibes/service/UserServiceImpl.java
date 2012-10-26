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
	

}
