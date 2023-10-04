package org.jsp.merchanttbootapp.execption;

public class IdNotFoundException extends RuntimeException {
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid Merchant id";
	}

}
