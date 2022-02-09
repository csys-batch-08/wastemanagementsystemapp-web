package com.cleaningmanagement.exception;

public class FoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	static final String GETMESSAGE="Employee not found/inactive in the given location"; 
	@Override
	public String getMessage() {
		
		return GETMESSAGE;
	}
	static final String GETMESSAGE2="insufficient Balance";
	public String getMessage2() {
		
		return GETMESSAGE2;
	}
	static final String GETMESSAGE3="emailId is Already used";
	public String getMessage3() {
		
		return GETMESSAGE3;
	}
	static final String GETMESSAGE4="you are inactive";
	public String getMessage4() {
		return GETMESSAGE4;
		
	}
	static final String GETMESSAGE5="you can't change the status since the employee status is in pending / inprogress state";
	public String getMessage5() {
		return GETMESSAGE5;
		
	}
	static final String NORESULTFOUND="No Result Found";
	public String noResultFound() {
		return NORESULTFOUND;
		
	}
	static final String ALREADYCANCELLED="Already Cancelled";
	public String alreadyCancelled()
	{
		return ALREADYCANCELLED;
	}
	static final String STATUSCHANGE="you Can't Change the status Since the RequestStatus is in Cancel/Completed State";
	public String statusChange()
	{
		return STATUSCHANGE;
	}
	static final String Location="Employee has been already added to this location";
	public String  locationUsed()
	{
		return Location;
	}
}
