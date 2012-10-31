package com.goodvibes.dao;

import static org.junit.Assert.assertNotNull;

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
public class UserDaoImplHibernateTests extends AbstractTestNGSpringContextTests {

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
	 * When everything is fine - test cases
	 */
	@Test
	public void shouldAcceptRegistrationAndReturnUserWithId() throws Exception{
		user = userDao.registerUser(user);
		assertNotNull(user.getId()) ;
	}

	/*
	 * Username - test cases
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveAsUsernameTooLong() throws Exception{
		user.setUsername("lkjadlskjasdasldjdaslkjasdlkajsdlaksdjasdasdas");
		user = userDao.registerUser(user);
	}
	
	@Test(expectedExceptions = org.hibernate.PropertyValueException.class)
	public void shouldRejectSaveAsUsernameIsNull() throws Exception{
		user.setUsername(null);
		user = userDao.registerUser(user);
	}
	
	@Test(dependsOnMethods = "shouldAcceptRegistrationAndReturnUserWithId", expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void shouldRejectSaveAsUsernameUnavailable() throws Exception{
		user.setEmail("michael298@email.com"); // must be rejected because of username being duplicated
		userDao.registerUser(user);
	}

	/*
	 * Email - test cases
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsEmailTooLong() throws Exception{
		user.setEmail("asdjasdpjasdkléjasdlkjadlkadjlkajdlaksdjlaskdjdadadasdasdasdasdasdasdasdsadasdasdasdasdasds@email.com");
		userDao.registerUser(user);
	}
	
	@Test(expectedExceptions = org.hibernate.PropertyValueException.class)
	public void shouldRejectSaveAsEmailIsNull() throws Exception{
		user.setEmail(null);
		userDao.registerUser(user);
	}
	
	@Test(dependsOnMethods = "shouldAcceptRegistrationAndReturnUserWithId", expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void shouldRejectSaveAsEmailUnavailable() throws Exception{
		user.setUsername("James09081237");   // must be rejected because of email being duplicated
		userDao.registerUser(user);
	}
	
	/*
	 * Password - test cases
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsPasswordTooLong() throws Exception{
		user.setPassword("password12213098098834343dsfsdfsldfhhlhldsfhsldfhlsdfkhldsfkhsdlfkhsdflh12312039809808123123090980213");
		userDao.registerUser(user);
	}

	@Test(expectedExceptions = org.hibernate.PropertyValueException.class)
	public void shouldRejectSaveOrUpdateAsPasswordIsNull() throws Exception{
		user.setPassword(null);
		userDao.registerUser(user);
	}

	/*
	 * Active/Enabled - no test cases as type is boolean, no way to make it null
	 */

	/*
	 * Lang - test cases
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsLangTooLong() throws Exception{
		user.setLanguage("english_slang_forbidden_because_too_long");
		userDao.registerUser(user);
	}

	/*
	 * First name - test cases
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsFirstNameTooLong() throws Exception{
		user.setFirstName("first name faaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaarr too long");
		userDao.registerUser(user);
	}
	
	/*
	 * Last name - test cases
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsLastNameTooLong() throws Exception{
		user.setLastName("LAST name faaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaarr too long");
		userDao.registerUser(user);
	}
	
	/*
	 * Gender - no test cases, it's a char type so can't be longer than one character...
	 */
	
	/*
	 * Location - test cases
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsLocationTooLong() throws Exception{
		user.setLocation("the place with the loooongest name in the whole entire world");
		userDao.registerUser(user);
	}

	/*
	 * Dob - No test cases as there is no constraint defined on the DAO layer
	 */

	/*
	 * Websites - test cases
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsWebsitesTooLong() throws Exception{
		user.setWebsites("the WEBSITES with the loooongest names in the whole entire world Wide WEB");
		userDao.registerUser(user);
	}
	
	/*
	 * Interests - test cases
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsInterestsTooLong() throws Exception{
		user.setInterests("I have looooooads of interests...I have looooooads of interests...I have looooooads " +
				"of interests...I have looooooads of interests...I have looooooads of interests...I have looooooads " +
				"of interests...I have looooooads of interests...I have looooooads of interests...I have looooooads " +
				"of interests...I have looooooads of interests...I have looooooads of interests...I have looooooads of interests...");
		userDao.registerUser(user);
	}
	
	
	
	/*
	 * IMAGE's type - test cases
	 */
	@Test
	public void shouldRejectSaveOrUpdateAsTypeIsNull() throws Exception{
		assert(false);
	}

	/*
	 * IMAGE's name - test cases
	 */
	@Test
	public void shouldRejectSaveOrUpdateAsNameIsTooLong() throws Exception{
		assert(false);
	}


	/*
	 * IMAGE's content type - test cases
	 */
	@Test
	public void shouldRejectSaveOrUpdateAsContentTypeIsTooLong() throws Exception{
		assert(false);
	}
	
	@Test
	public void shouldRejectSaveOrUpdateAsContentTypeIsNull() throws Exception{
		assert(false);
	}

	
	/*
	 * IMAGE's length - test cases
	 */	
	@Test
	public void shouldRejectSaveOrUpdateAsLengthIsNull() throws Exception{
		assert(false);
	}
	
}
