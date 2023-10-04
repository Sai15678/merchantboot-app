package org.jsp.merchanttbootapp.service;

import java.util.Optional;

import org.jsp.merchanttbootapp.dao.MerchantDao;
import org.jsp.merchanttbootapp.dto.Merchant;
import org.jsp.merchanttbootapp.dto.ResponseStructure;
import org.jsp.merchanttbootapp.execption.IdNotFoundException;
import org.jsp.merchanttbootapp.execption.InvalidCredentialsExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class MerchantService {
	@Autowired
	private MerchantDao dao;
	
	
	public ResponseEntity<ResponseStructure<Merchant>>  saveMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		merchant =dao.saveMerchant(merchant);
		structure.setData(merchant);
		structure.setMessage("Merchants registered Succesfully"+merchant.getId());
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant( Merchant merchant) {
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		merchant= dao.updateMerchant(merchant);
		structure.setData(merchant);
		structure.setMessage("Merchant updated succesfully");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
		
		
	}
	
	
	public ResponseEntity<ResponseStructure<Merchant>> findById( int id) {
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		Optional<Merchant>reMerchant = dao.findById(id);
		if(reMerchant.isPresent()) {
			structure.setData(reMerchant.get());
			structure.setMessage("merchant");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
		
	
	}
	
	public ResponseEntity<ResponseStructure<String>>  deleteById( int id) {
		ResponseStructure<String> structure=new ResponseStructure<>();
		boolean deleted=dao.deleteById(id);
		if(deleted) {
			structure.setData("Merchant deleted");
			structure.setMessage("merchant found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setData("Merchant not deleted");
		structure.setMessage("Invalid Merchant id");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant( long phone, String password) {
		Optional<Merchant> recMerchant=dao.verifyMerchant(phone, password);
		ResponseStructure<Merchant> structure=new ResponseStructure<>();
		if(recMerchant.isPresent()) {
			structure.setData(recMerchant.get());
			structure.setMessage("merchant Verfied");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK) ;
		}
		throw new InvalidCredentialsExceptions();
		
	}

}
