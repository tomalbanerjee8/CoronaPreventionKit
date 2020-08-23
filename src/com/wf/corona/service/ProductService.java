package com.wf.corona.service;

import java.util.List;

import com.wf.corona.exception.CkException;
import com.wf.corona.model.ProductItem;


public interface ProductService {

	ProductItem	validateAndAdd(ProductItem productMaster) throws CkException;
	ProductItem	validateAndSave(ProductItem productMaster) throws CkException;
	  
	  boolean deleteProductMaster(Integer productMasterId) throws CkException;
	  
	  ProductItem getProductMaster(Integer productMasterId) throws CkException; 
	  List<ProductItem> getAllProductMasters() throws CkException;
	 
}
