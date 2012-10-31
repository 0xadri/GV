package com.goodvibes.dao;

import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.goodvibes.model.RoleEntity;
import com.goodvibes.model.GoodVibeUserDetails;


@Repository
public class UserDaoImplHibernate implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	// You want to make sure you test this method independently from Spring Authentication
	// Spring Authentication will simply NOT authenticate you if this throws an error (no stack trace displayed)
	@Override
	@Transactional(readOnly = true)
	public GoodVibeUserDetails findByUsernameOrEmail(String usernameOrEmail) {
		
		String hql = "FROM GoodVibeUserDetails WHERE username = '" + usernameOrEmail + "' or email = '" + usernameOrEmail + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		GoodVibeUserDetails userDetails = (GoodVibeUserDetails) query.uniqueResult();
		
		if (userDetails == null){
			throw new UsernameNotFoundException("user not found for username or email: " + usernameOrEmail);
		}
				
		return userDetails;
	}


	@Override
	@Transactional(readOnly = true)
	public GoodVibeUserDetails getDetailsRolesAndImagesForUser(String username) {

		String hql = "FROM GoodVibeUserDetails WHERE username = '" + username + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		GoodVibeUserDetails userDetails = (GoodVibeUserDetails) query.uniqueResult();
		
		if (userDetails == null){
			throw new UsernameNotFoundException("user not found for username : " + username );
		}

		Hibernate.initialize(userDetails.getRoles());
		Hibernate.initialize(userDetails.getImages());

		return userDetails;
	}

	@Override
	@Transactional
	public GoodVibeUserDetails registerUser(GoodVibeUserDetails user) {

		sessionFactory.getCurrentSession().save(user);
		// return the user with its generated ID
		
		// FIXME: The only safe thing to do after an exception with the session, is to rollback the transaction and close it. Any other type of interaction will probably generate another exception (in this case an assertion exception).
		
		return user;
	}
	
//	@Override
//	public void updateDetailsForUser(GoodVibeUserDetails user) {
//
//		sessionFactory.getCurrentSession().update(user);
//		
//		return null;
//	}

}
