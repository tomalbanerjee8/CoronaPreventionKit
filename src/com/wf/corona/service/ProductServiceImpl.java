package com.wf.corona.service;

import java.util.ArrayList;
import java.util.List;

import com.wf.corona.dao.ProductItemDao;
import com.wf.corona.dao.ProductItemDaoImpl;
import com.wf.corona.exception.CkException;
import com.wf.corona.model.ProductItem;

public class ProductServiceImpl implements ProductService {
	ProductItemDao productMasterDao;
	
	public ProductServiceImpl() {
		productMasterDao=new ProductItemDaoImpl();
	}

	
	private boolean isValidProdId(Integer prodyuctId) {
		return prodyuctId!=null && prodyuctId>0;
	}
	private boolean isValidProdCost(Double prodCost) {
		return prodCost!=null && prodCost>0;
	}

	private boolean isValidProdDesc(String prodDesc) {
		return prodDesc!=null && (prodDesc.length()>0 && prodDesc.length()<400);
	}
	private boolean isValidProdName(String prodName) {
		return prodName!=null && (prodName.length()>0 && prodName.length()<100);
	}
	
	private boolean isValidProduct(ProductItem prod) throws CkException{
		boolean isValid=true;
		
		List<String> errMsgs = new ArrayList<>();
		
		if(prod!=null) {
			if(!isValidProdId(prod.getId())) {
				isValid=false;
				errMsgs.add("Loan Id must be a positive non-repetative number");
			}
			if(!isValidProdCost(prod.getCost())) {
				isValid=false;
				errMsgs.add("Principal must be a positive non-repetative number");
			}
			if(!isValidProdDesc(prod.getProductDescription())) {
				isValid=false;
				errMsgs.add("Rate of Interest must be between 0 and 1");
			}
			if(!isValidProdName(prod.getProductName())) {
				isValid=false;
				errMsgs.add("Disbursment Date must be a past date");
			}
			
			if(!errMsgs.isEmpty()) {
				throw new CkException(errMsgs.toString());
			}
		}else {
			isValid=false;
		}
		
		return isValid;
	}
	@Override
	public ProductItem validateAndSave(ProductItem productMaster) throws CkException {
		if(isValidProduct(productMaster)) {
			productMasterDao.save(productMaster);
		}
		return productMaster;
	}

	@Override
	public boolean deleteProductMaster(Integer productMasterId) throws CkException {
		return productMasterDao.deleteById(productMasterId);
	}

	@Override
	public ProductItem getProductMaster(Integer productMasterId) throws CkException {
		return productMasterDao.getById(productMasterId);
	}

	@Override
	public List<ProductItem> getAllProductMasters() throws CkException {
		return productMasterDao.getAll();
	}
	@Override
	public ProductItem validateAndAdd(ProductItem productMaster) throws CkException {
		if(isValidProduct(productMaster)) {
			System.out.println("in impl");
			productMasterDao.add(productMaster);
		}
		return productMaster;
	}

}
