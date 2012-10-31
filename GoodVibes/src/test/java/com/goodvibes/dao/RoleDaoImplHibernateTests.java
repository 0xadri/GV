package com.goodvibes.dao;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.goodvibes.model.RoleEntity;
import java.text.ParseException;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class RoleDaoImplHibernateTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private RoleDao roleDao;

	private RoleEntity role; 

	@BeforeMethod
	public void beforeEachMethod() throws ParseException{
		role = new RoleEntity();
		role.setRoleName("anonymous");
		// FIXME: Drop database and recreate it along with tables
	}

	
	/*
	 * Save when everything is fine - test cases
	 */
	@Test
	public void shouldAcceptSaveAndReturnRoleWithId() throws Exception{
		//Given
		assertNull(role.getId()) ;
		//When
		role = roleDao.saveRole(role);
		//Then
		assertNotNull(role.getId()) ;
	}
	
	@Test
	public void shouldAcceptSaveAndReturnDifferentIds() throws Exception{
		//Given
		role.setRoleName("admin");
		role = roleDao.saveRole(role);
		int roleOneId = role.getId();
		
		//When
		role.setRoleName("master");
		role = roleDao.saveRole(role);
		int roleTwoId = role.getId();
		
		//Then
		assertThat(roleOneId,not(roleTwoId));
	}
	
	/*
	 * Save when everything is NOT fine: Role name - checking constraints..
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsRoleNameIsTooLong() throws Exception{
		//Given
		role.setRoleName("super hiper mega powerful uuuuuuuuuuuuuuuuuuuuuser");
		//When
		roleDao.saveRole(role);
		//Then
		//cf expected exception		
	}

	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void shouldRejectSaveOrUpdateAsRoleIsDuplicate() throws Exception{
		//Given
		// we do not change role setup in beforeEachMethod()
		//When
		roleDao.saveRole(role);
		roleDao.saveRole(role);
		//Then
		//cf expected exception
	}

	@Test(expectedExceptions = org.hibernate.PropertyValueException.class)
	public void shouldRejectSaveOrUpdateAsRoleIsNull() throws Exception{
		//Given
		role.setRoleName(null);
		//When
		roleDao.saveRole(role);
		//Then
		//cf expected exception
	}

	/*
	 * getRoleEntityForRoleName - test cases
	 */
	@Test
	public void shouldReturnCorrectRoleEntityForRoleName() throws Exception{
		final String roleName = "supamaster";
		//Given
		role.setRoleName(roleName);
		roleDao.saveRole(role);
		//When
		RoleEntity roleReceived = roleDao.getRoleEntityForRoleName(roleName);
		//Then
		assertThat(role,equalTo(roleReceived));
	}
	
	@Test(expectedExceptions = com.goodvibes.exception.RoleNotFoundException.class)
	public void shouldThrowExceptionAsRoleNameDoesNotExist() throws Exception{
		roleDao.getRoleEntityForRoleName("randomRoleName");
	}
	
	/*
	 * getRoleEntityForRoleId - test cases
	 */
	@Test
	public void shouldReturnCorrectRoleEntityForId() throws Exception{
		//Given
		roleDao.saveRole(role);
		int id = role.getId();
		//When
		RoleEntity roleReceived = roleDao.getRoleEntityForRoleId(id);
		//Then
		assertThat(role,equalTo(roleReceived));
	}
	
	@Test(expectedExceptions = com.goodvibes.exception.RoleNotFoundException.class)
	public void shouldThrowExceptionAsIdDoesNotExist() throws Exception{
		roleDao.getRoleEntityForRoleId(908798);
	}
	
	/*
	 * getRoleAndCollectionOfUsersForRoleId - test cases
	 */
	@Test
	public void shouldReturnCorrectUsersForRoleId() throws Exception{
		//Given
		roleDao.saveRole(role);
		int id = role.getId();
		//When
		RoleEntity roleReceived = roleDao.getRoleEntityForRoleId(id);
		//Then
		assertThat(role,equalTo(roleReceived));
	}


	
	
}
