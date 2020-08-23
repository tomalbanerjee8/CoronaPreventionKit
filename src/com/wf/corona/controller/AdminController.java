package com.wf.corona.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wf.corona.dao.ProductItemDao;
import com.wf.corona.model.ProductItem;


//@WebServlet({ "/admin", "/login","/insertProduct", "/newproduct", "/addLoan", "/editLoan", "/saveLoan" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductItemDao productMasterDao;
	
	
	public void setProductMasterDao(ProductItemDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		String viewName = "";
		try {
			switch (action) {
			case "/admin" : 
				viewName = adminLogin(request, response);
				break;
			case "login" : 
				viewName = adminLoginNext(request, response);
				break;
			case "addnewproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
				
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	
	private String insertProduct(HttpServletRequest request, HttpServletResponse response) {
		
		ProductItem pm = new ProductItem(); 
		pm.setId(Integer.parseInt(request.getParameter("id")));
		pm.setProductDescription(request.getParameter("productDescription"));
		pm.setProductName(request.getParameter("productName"));
		pm.setCost(Double.parseDouble(request.getParameter("cost")));
		
		String view="";
		
		
		return view;
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		
		ProductItem item = new ProductItem();
		request.setAttribute("productMaster", item);
		
		return "newproduct.jsp";
		
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String view = "AdminUserLogin.jsp";

		return view;
	}
	
private String adminLoginNext(HttpServletRequest request, HttpServletResponse response) {
		

	request.setAttribute("loginsuccess", true);

		return "AdminUserLogin.jsp";
	}

	
}