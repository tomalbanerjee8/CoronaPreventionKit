package com.wf.corona.dao;

import java.util.List;

import com.wf.corona.exception.CoronaException;
import com.wf.corona.model.ProductMaster;




public interface ProductMasterDao {
	
	ProductMaster add(ProductMaster productMaster) throws CoronaException;
	ProductMaster save(ProductMaster productMaster) throws CoronaException;
	boolean deleteById(Integer productMasterdId) throws CoronaException;
	
	List<ProductMaster> getAll() throws CoronaException;
	ProductMaster getById(Integer ProductMasterId) throws CoronaException;
	
	 
	 
}