package com.goodvibes.model.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.goodvibes.model.GoodVibeUserDetails;
import com.goodvibes.service.UserServiceImpl;


@Component
public class GoodVibeUserDetailsValidator implements Validator {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return GoodVibeUserDetails.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		String usernameFieldName = "username";
		String emailFieldName = "email";
		String passwordFieldName = "password";
		String passwordConfirmationFieldName = "passwordConfirmation";
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, usernameFieldName, "required.username", "Username is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, emailFieldName, "required.email", "Email is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, passwordFieldName, "required.password", "Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, passwordConfirmationFieldName, "required.passwordConfirmation", "Password Confirmation is required");

		GoodVibeUserDetails user = (GoodVibeUserDetails) target;
		
		String username = user.getUsername();
		if(username!=null && errors.getFieldErrors(usernameFieldName).size()==0){
			if(username.length()>45) errors.rejectValue(usernameFieldName, "tooLong.username","username is too long");
			if(username.length()<3) errors.rejectValue(usernameFieldName, "tooShort.username","username is too short");
		}
		
		String email = user.getEmail();
		if(email!=null && errors.getFieldErrors(emailFieldName).size()==0){
			if(email.length()>45) errors.rejectValue(emailFieldName, "tooLong.email","email is too long");
			if(email.length()<3) errors.rejectValue(emailFieldName, "tooShort.email","email is too short");
		}
		
		// TODO: check email format is valid (regex for ...@...dot...)
		
		String password = user.getPassword();
		if(password!=null && errors.getFieldErrors(passwordFieldName).size()==0){
			if(password.length()>100) errors.rejectValue(passwordFieldName, "tooLong.password","Password is too long");
			if(password.length()<4) errors.rejectValue(passwordFieldName, "tooShort.password","Password is too short");
		}

		String passwordConf = user.getPasswordConfirmation();

		if(password!=null && passwordConf!=null && errors.getFieldErrors(passwordFieldName).size()==0 && errors.getFieldErrors(passwordConfirmationFieldName).size()==0){
			if( !password.equals(passwordConf) ){
				errors.rejectValue(passwordFieldName, "mismatch.password","Passwords do not match");
				errors.rejectValue(passwordConfirmationFieldName, "mismatch.passwordConfirmation","Passwords do not match");
			}
		}
		
		// TODO: check if password is complicated enough maybe?

		// TODO: add Captcha for sign up page ONLY
		
		if(errors.getAllErrors().size() == 0){

			if ( userService.doesUsernameAlreadyExist( user.getUsername() ) )
				errors.rejectValue(usernameFieldName, "duplicate.username","username already exists");
			
			if ( userService.doesEmailAlreadyExist( user.getEmail() ) )
				errors.rejectValue(emailFieldName, "duplicate.email","email already exists");
			
		}

	}
	
}
