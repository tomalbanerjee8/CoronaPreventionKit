package com.wf.corona.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wf.corona.exception.CkException;
import com.wf.corona.model.ProductItem;

public class ProductItemDaoImpl implements ProductItemDao {
	
	public static final String INS_ITEM_QRY = "INSERT INTO productitems(id,productdesc,productname,cost) values(?,?,?,?)";
	public static final String UPD_ITEM_QRY = "UPDATE productitems SET productdesc=?,productname=?,cost=? WHERE id=?";
	public static final String DEL_ITEM_QRY = "DELETE FROM productitems WHERE id=?";
	public static final String SEL_ITEM_QRY_BY_ID = "SELECT id,productdesc,productname,cost FROM productitems WHERE id=?";
	public static final String SEL_ALL_ITEMS_QRY = "SELECT id,productdesc,productname,cost FROM productitems";

	@Override
	public ProductItem add(ProductItem productMaster) throws CkException {
		if (productMaster != null) 
		{ 
			try (Connection con = ConnectionFactory.getConnection(); 
					PreparedStatement pst =
		  con.prepareStatement(INS_ITEM_QRY)) {
		  
		  pst.setInt(1, productMaster.getId()); 
		  pst.setString(2, productMaster.getProductDescription());
		  pst.setString(3, productMaster.getProductName()); 
		  pst.setDouble(4, productMaster.getCost());
		  
		  pst.executeUpdate();
		  
		  } 
			catch (SQLException  exp) {
				exp.printStackTrace();
				throw new CkException("Adding Product failed!"); } } 
		return productMaster;
	}

	@Override
	public ProductItem save(ProductItem productMaster) throws CkException {
		if (productMaster != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_ITEM_QRY);) {

				pst.setString(1, productMaster.getProductDescription());
				pst.setString(2, productMaster.getProductName());
				pst.setDouble(3, productMaster.getCost());
				pst.setInt(4, productMaster.getId());
				pst.executeUpdate();

			} catch (SQLException exp) {
				exp.printStackTrace();
				throw new CkException("An error occured, Could not able to edit product");
			}
		}
		return productMaster;
	}

	@Override
	public boolean deleteById(Integer productMasterdId) throws CkException {
		boolean isDeleted = false;

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_ITEM_QRY);) {

			pst.setInt(1, productMasterdId);

			int rowsCount = pst.executeUpdate();
			
			isDeleted= rowsCount>0 ;

		} catch (SQLException exp) {
			throw new CkException("An error occured, Could not able to delete the product!");
		}

		return isDeleted;
	}

	@Override
	public List<ProductItem> getAll() throws CkException {
		
		List<ProductItem> products = new ArrayList<>();
		
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ALL_ITEMS_QRY);) {		

			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				ProductItem pm = new ProductItem();
				pm.setId(rs.getInt(1));
				pm.setProductDescription(rs.getString(2));
				pm.setProductName(rs.getString(3));
				pm.setCost(rs.getDouble(4));
				
				products.add(pm);
			}
			
			if(products.isEmpty()) {
				products=null;
			}
		} catch (SQLException exp) {
			throw new CkException("An error occured, Could not able to retrive the product!");
		}
				
		return products;
	}

	@Override
	public ProductItem getById(Integer productMasterId) throws CkException {
		
		ProductItem product=null;
		
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ITEM_QRY_BY_ID);) {		

			pst.setInt(1, productMasterId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				product = new ProductItem();
				product.setId(rs.getInt(1));
				product.setProductDescription(rs.getString(2));
				product.setProductName(rs.getString(3));
				product.setCost(rs.getDouble(4));
			}
			
		} catch (SQLException exp) {
			throw new CkException("An error occured, Could not able to retrive the product!");
		}
		
		return product;
	}
}
