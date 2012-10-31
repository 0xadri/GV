package com.goodvibes.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

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
import com.goodvibes.model.UserImage;


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
		user.setInterests("loads");
		user.setLanguage("en");
		user.setLastName("Jo");
		user.setLocation("Moon");
		user.setPassword("averylongpassword");
		user.setWebsites("mysite.com");
		// note that images and roles are left out
		
		// FIXME: Drop database and recreate it along with tables
	}

	/*
	 * registerUser() - When everything is fine
	 */
	@Test
	public void shouldAcceptRegistrationAndReturnUserWithId() throws Exception{
		//Given
		assertNull(user.getId()) ;
		//When
		user = userDao.registerUser(user);
		//Then
		assertNotNull(user.getId()) ;
	}

	/*
	 * registerUser() - Username
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveAsUsernameTooLong() throws Exception{
		//Given
		user.setUsername("lkjadlskjasdasldjdaslkjasdlkajsdlaksdjasdasdas");
		//When
		user = userDao.registerUser(user);
		//Then
		//cf expected exception
	}
	
	@Test(expectedExceptions = org.hibernate.PropertyValueException.class)
	public void shouldRejectSaveAsUsernameIsNull() throws Exception{
		//Given
		user.setUsername(null);
		//When
		user = userDao.registerUser(user);
		//Then
		//cf expected exception
	}
	
	@Test(dependsOnMethods = "shouldAcceptRegistrationAndReturnUserWithId", expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void shouldRejectSaveAsUsernameUnavailable() throws Exception{
		//Given
		user.setEmail("michael298@email.com"); // must be rejected because of username being duplicated
		//When
		userDao.registerUser(user);
		//Then
		//cf expected exception
	}

	/*
	 * registerUser() - Email
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsEmailTooLong() throws Exception{
		//Given
		user.setEmail("asdjasdpjasdkléjasdlkjadlkadjlkajdlaksdjlaskdjdadadasdasdasdasdasdasdasdsadasdasdasdasdasds@email.com");
		//When
		userDao.registerUser(user);
		//Then
		//cf expected exception
	}
	
	@Test(expectedExceptions = org.hibernate.PropertyValueException.class)
	public void shouldRejectSaveAsEmailIsNull() throws Exception{
		//Given
		user.setEmail(null);
		//When
		userDao.registerUser(user);
		//Then
		//cf. expected exception
	}
	
	@Test(dependsOnMethods = "shouldAcceptRegistrationAndReturnUserWithId", expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void shouldRejectSaveAsEmailUnavailable() throws Exception{
		//Given
		user.setUsername("James09081237");   // must be rejected because of email being duplicated
		//When
		userDao.registerUser(user);
		//Then
		//cf. expected exception
	}
	
	/*
	 * registerUser() - Password
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsPasswordTooLong() throws Exception{
		//Given
		user.setPassword("password12213098098834343dsfsdfsldfhhlhldsfhsldfhlsdfkhldsfkhsdlfkhsdflh12312039809808123123090980213");
		//When
		userDao.registerUser(user);
		//Then
		//cf. expected exception
	}

	@Test(expectedExceptions = org.hibernate.PropertyValueException.class)
	public void shouldRejectSaveOrUpdateAsPasswordIsNull() throws Exception{
		//Given
		user.setPassword(null);
		//When
		userDao.registerUser(user);
		//Then
		//cf. expected exception
	}

	/*
	 * registerUser() - Active/Enabled - no test cases as type is boolean, no way to make it null
	 */

	/*
	 * registerUser() - Lang
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsLangTooLong() throws Exception{
		//Given
		user.setLanguage("english_slang_forbidden_because_too_long");
		//When
		userDao.registerUser(user);
		//Then
		//cf. expected exception
	}

	/*
	 * registerUser() - First name
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsFirstNameTooLong() throws Exception{
		//Given
		user.setFirstName("first name faaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaarr too long");
		//When
		userDao.registerUser(user);
		//Then
		//cf. expected exception
	}
	
	/*
	 * registerUser() - Last name
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsLastNameTooLong() throws Exception{
		//Given
		user.setLastName("LAST name faaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaarr too long");
		//When
		userDao.registerUser(user);
		//Then
		//cf. expected exception
	}
	
	/*
	 * registerUser() - Gender - no test cases, it's a char type so can't be longer than one character...
	 */
	
	/*
	 * registerUser() - Location
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsLocationTooLong() throws Exception{
		//Given
		user.setLocation("the place with the loooongest name in the whole entire world");
		//When
		userDao.registerUser(user);
		//Then
		//cf. expected exception
	}

	/*
	 * registerUser() - Dob - No test cases as there is no constraint defined on the DAO layer
	 */

	/*
	 * registerUser() - Websites
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsWebsitesTooLong() throws Exception{
		//Given
		user.setWebsites("the WEBSITES with the loooongest names in the whole entire world Wide WEB");
		//When
		userDao.registerUser(user);
		//Then
		//cf. expected exception
	}
	
	/*
	 * registerUser() - Interests
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsInterestsTooLong() throws Exception{
		//Given
		user.setInterests("I have looooooads of interests...I have looooooads of interests...I have looooooads " +
				"of interests...I have looooooads of interests...I have looooooads of interests...I have looooooads " +
				"of interests...I have looooooads of interests...I have looooooads of interests...I have looooooads " +
				"of interests...I have looooooads of interests...I have looooooads of interests...I have looooooads of interests...");
		//When
		userDao.registerUser(user);
		//Then
		//cf. expected exception
	}
	
	
	/*
	 * registerUser() - IMAGE's type can't be put to null (primitive)
	 */

	/*
	 * registerUser() - IMAGE's name
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsNameIsTooLong() throws Exception{

		UserImage image = new UserImage();
		image.setType(1);
		image.setContentType("whatever");
		image.setLength(500);
		image.setName("aNameFarTooLooooooooooooooooooooooooooooooooooooooooooongToBeAccepted");
		Set<UserImage> imageCollection = user.getImages();
		imageCollection.add(image);
		user.setImages(imageCollection);
		
		userDao.registerUser(user);
	}

	/*
	 * registerUser() - IMAGE's content type - test cases
	 */
	@Test(expectedExceptions = org.hibernate.exception.DataException.class)
	public void shouldRejectSaveOrUpdateAsContentTypeIsTooLong() throws Exception{

		UserImage image = new UserImage();
		image.setType(1);
		image.setContentType("contentTypeIsFarTooLooooooooooooooooooooooooooooooooooooooooooong");
		image.setLength(500);
		Set<UserImage> imageCollection = user.getImages();
		imageCollection.add(image);
		user.setImages(imageCollection);
		
		userDao.registerUser(user);
	}
	
	@Test(expectedExceptions = org.hibernate.PropertyValueException.class)
	public void shouldRejectSaveOrUpdateAsContentTypeIsNull() throws Exception{

		UserImage image = new UserImage();
		image.setType(1);
		image.setContentType(null);
		image.setLength(500);
		Set<UserImage> imageCollection = user.getImages();
		imageCollection.add(image);
		user.setImages(imageCollection);
		
		userDao.registerUser(user);
	}

	
	/*
	 * registerUser() - IMAGE's length can't be put to null (primitive)
	 */

	/*
	 * findByUsernameOrEmail() - test cases
	 */	
	@Test
	public void shouldFindUserMatchingUsername() throws Exception{
		final String username = "myCoolUser";
		
		user.setUsername(username);
		userDao.registerUser(user);
		
		GoodVibeUserDetails userReceived = userDao.findByUsernameOrEmail(username);
		
		assertThat(user,equalTo(userReceived));
	}
	
	@Test
	public void shouldFindUserMatchingEmail() throws Exception{
		final String email = "myCoolUser@gmail.com";
		
		user.setEmail(email);
		userDao.registerUser(user);
		
		GoodVibeUserDetails userReceived = userDao.findByUsernameOrEmail(email);
		
		assertThat(user,equalTo(userReceived));
	}
	
	@Test(expectedExceptions = org.springframework.security.core.userdetails.UsernameNotFoundException.class )
	public void shouldThrowExceptionAsUsernameOrEmailDoesNotExist() throws Exception{
		userDao.findByUsernameOrEmail("randomUserOrEmail");
	}
	
	/*
	 * getDetailsRolesAndImagesForUser() - test cases
	 */	
	@Test
	public void shouldBeAbleToAccessRolesAndImages() throws Exception{
		
		UserImage image = new UserImage();
		image.setType(1);
		image.setContentType("whatever");
		image.setLength(500);
		Set<UserImage> imageCollection = user.getImages();
		imageCollection.add(image);
		user.setImages(imageCollection);
		
		RoleEntity role = new RoleEntity();
		role.setRoleName("masterSupaRole");
		Set<RoleEntity> roleCollection = user.getRoles();
		roleCollection.add(role);
		user.setRoles(roleCollection);
		
		final String username = "myCoolUser";
		user.setUsername(username);
		
		userDao.registerUser(user);
		
		GoodVibeUserDetails userReceived = userDao.getDetailsRolesAndImagesForUser(username);
		
		assert(userReceived.getImages().contains(image));
		assert(userReceived.getRoles().contains(role));
	}	
	
}
