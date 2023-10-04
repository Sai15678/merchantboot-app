package org.jsp.merchanttbootapp.execption;



public class InvalidCredentialsExceptions extends RuntimeException {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid PhoneNo Or Password";
	}
	
}
