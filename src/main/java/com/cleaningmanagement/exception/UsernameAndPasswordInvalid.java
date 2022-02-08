package com.cleaningmanagement.exception;

public class UsernameAndPasswordInvalid extends Exception {
    
    static final String GETMESSAGE="UserName and PassWord Invalid";
	@Override
	public String getMessage() {
		return GETMESSAGE;
		
	}
	static final String GETMESSAGE4="you are inactive";
	public String getMessage1() {
		return GETMESSAGE4;
		
	}
}
