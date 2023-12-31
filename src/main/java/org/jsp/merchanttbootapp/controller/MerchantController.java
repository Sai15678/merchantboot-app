package org.jsp.merchanttbootapp.controller;

import java.util.Optional;

import org.jsp.merchanttbootapp.dao.MerchantDao;
import org.jsp.merchanttbootapp.dto.Merchant;
import org.jsp.merchanttbootapp.dto.ResponseStructure;
import org.jsp.merchanttbootapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantController {
	@Autowired
	private MerchantService service;
	
	@PostMapping("/merchants")
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant) {
		
		return service.saveMerchant(merchant);
	}
	
	@PutMapping("/merchants")
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		
		return service.updateMerchant(merchant);
		
	}
	
	@GetMapping("/merchants/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable int id) {
		
			return service.findById(id);
	}
		

	
	@DeleteMapping("/merchants/{id}")
	public ResponseEntity< ResponseStructure<String>> deleteById(@PathVariable int id) {
		
		return service.deleteById(id);
	}
	
	@PostMapping("merchants/verify-by-phone")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam long phone,@RequestParam String password) {
	
		return service.verifyMerchant(phone, password);
		
	}
}