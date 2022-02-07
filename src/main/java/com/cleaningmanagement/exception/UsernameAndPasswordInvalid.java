package com.cleaningmanagement.exception;

public class UsernameAndPasswordInvalid extends Exception {
    
    static final String GETMESSAGE="UserName and PassWord Invalid";
	public String getMessage() {
		return GETMESSAGE;
		
	}
}
