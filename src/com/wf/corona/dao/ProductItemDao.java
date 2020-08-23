package com.wf.corona.dao;

import java.util.List;

import com.wf.corona.exception.CkException;
import com.wf.corona.model.ProductItem;




public interface ProductItemDao {
	
	ProductItem add(ProductItem productMaster) throws CkException;
	ProductItem save(ProductItem productMaster) throws CkException;
	boolean deleteById(Integer productMasterdId) throws CkException;
	
	List<ProductItem> getAll() throws CkException;
	ProductItem getById(Integer ProductMasterId) throws CkException;
	
	 
	 
}