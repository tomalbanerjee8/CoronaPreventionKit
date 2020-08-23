package com.wf.corona.service;

import java.util.List;

import com.wf.corona.exception.CoronaException;
import com.wf.corona.model.ProductMaster;


public interface AdminService {

	ProductMaster	  validateAndAdd(ProductMaster productMaster) throws CoronaException;
ProductMaster	  validateAndSave(ProductMaster productMaster) throws CoronaException;
	  
	  boolean deleteProductMaster(Integer productMasterId) throws CoronaException;
	  
	  ProductMaster getProductMaster(Integer productMasterId) throws CoronaException; 
	  List<ProductMaster> getAllProductMasters() throws CoronaException;
	 
}
