package org.jsp.merchanttbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchanttbootapp.dao.MerchantDao;
import org.jsp.merchanttbootapp.dao.ProductDao;
import org.jsp.merchanttbootapp.dto.Merchant;
import org.jsp.merchanttbootapp.dto.Product;
import org.jsp.merchanttbootapp.dto.ResponseStructure;
import org.jsp.merchanttbootapp.execption.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao;
	@Autowired
	private MerchantDao merchantDao;
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product,int merchant_id){
		Optional<Merchant> recMerchant=merchantDao.findById(merchant_id);
		ResponseStructure<Product> structure=new ResponseStructure<>();
		if(recMerchant.isPresent()) {
			Merchant m=recMerchant.get();
			product.setMerchant(m);
			merchantDao.updateMerchant(m);
			dao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("Product added");
			structure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product,int merchant_id){
		Optional<Merchant> recMerchant=merchantDao.findById(merchant_id);
		ResponseStructure<Product> structure=new ResponseStructure<>();
		if(recMerchant.isPresent()) {
			Merchant m=recMerchant.get();
			product.setMerchant(m);
			dao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("Product added");
			structure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
			
	public ResponseEntity<ResponseStructure<Product>> findById(int id){
		ResponseStructure<Product> structure= new ResponseStructure<>();
		Optional<Product> recProduct=dao.findById(id);
		if(recProduct.isPresent()) {
			structure.setData(recProduct.get());
			structure.setMessage("Product Found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(int merchant_id){
		ResponseStructure<List<Product>> structure= new ResponseStructure<>();
		Optional<Merchant> recMerchant=merchantDao.findById(merchant_id);
		if(recMerchant.isPresent()) {
			structure.setData(dao.findByMerchantId(merchant_id));
			structure.setMessage("Product Found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.NOT_FOUND);
		}
		throw new IdNotFoundException();
	}
}
