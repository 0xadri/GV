package com.goodvibes.exception;

@SuppressWarnings("serial")
public class RoleNotFoundException extends Exception {

	public RoleNotFoundException() {}

    //Constructor that accepts a message
    public RoleNotFoundException(String message)
    {
       super(message);
    }
    
}
