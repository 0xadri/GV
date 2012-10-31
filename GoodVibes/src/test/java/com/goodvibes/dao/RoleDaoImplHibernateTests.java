package com.goodvibes.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.goodvibes.model.GoodVibeUserDetails;
import com.goodvibes.model.RoleEntity;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class RoleDaoImplHibernateTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserDao userDao;

	private GoodVibeUserDetails user; 

	@BeforeMethod
	public void beforeEachMethod() throws ParseException{
		user = new GoodVibeUserDetails();
		user.setUsername("adrien");
		user.setActive(true);
		user.setDob(new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse("January 2, 1983"));
		user.setEmail("adib@hotmail.com");
		user.setFirstName("adri");
		user.setGender('M');
		// FIXME: mock Image?
		//user.setImages(Set<UserImage>)
		user.setInterests("loads");
		user.setLanguage("en");
		user.setLastName("Jo");
		user.setLocation("Moon");
		user.setPassword("averylongpassword");
		user.setWebsites("mysite.com");
		
		RoleEntity role = new RoleEntity();
		role.setRoleName("anonymous");
		Set<RoleEntity> collectionOfRoles = user.getRoles();
		collectionOfRoles.add(role);
		user.setRoles(collectionOfRoles);
		
		// FIXME: Drop database and recreate it along with tables
	}

	/*
	 * ROLE's Rolename - test cases
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsRoleNameIsTooLong() throws Exception{
		RoleEntity role = new RoleEntity();
		role.setRoleName("super hiper mega powerful uuuuuuuuuuuuuuuuuuuuuser");
		Set<RoleEntity> collectionOfRoles = user.getRoles();
		collectionOfRoles.add(role);
		user.setRoles(collectionOfRoles);
		userDao.registerUser(user);
	}

	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void shouldRejectSaveOrUpdateAsRoleIsDuplicate() throws Exception{
		RoleEntity role = new RoleEntity();
		role.setRoleName("anonymous");
		Set<RoleEntity> collectionOfRoles = user.getRoles();
		collectionOfRoles.add(role);
		user.setRoles(collectionOfRoles);
		userDao.registerUser(user);
	}
	
	@Test
	public void shouldAcceptSaveOrUpdateAsRoleIsUnique() throws Exception{
		RoleEntity role = new RoleEntity();
		role.setRoleName("admin");
		Set<RoleEntity> collectionOfRoles = user.getRoles();
		collectionOfRoles.add(role);
		user.setRoles(collectionOfRoles);
		userDao.registerUser(user);
	}

	@Test
	public void shouldAcceptSaveOrUpdateAsSeveralUsersWithSameRoleIsAllowed() throws Exception{
		userDao.registerUser(user);
		
		user.setEmail("otherUser@email.com");
		user.setUsername("OtherUser");
		RoleEntity role = new RoleEntity();
		role.setRoleName("anonymous");
		Set<RoleEntity> collectionOfRoles = user.getRoles();
		collectionOfRoles.add(role);
		user.setRoles(collectionOfRoles);
		userDao.registerUser(user);		
	}

}
