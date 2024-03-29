package com.wf.corona.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wf.corona.exception.CkException;
import com.wf.corona.model.ProductItem;
import com.wf.corona.service.ProductService;
import com.wf.corona.service.ProductServiceImpl;


@WebServlet({ "/admin","/addproduct", "/insertproduct", "/saveproduct", "/listOfproductsAdmin", "/deleteProd" ,"/editProd","/logout","/login"})
public class AdminPortalController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private ProductService adminService;

	@Override
	public void init() throws ServletException {
		adminService = new ProductServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getServletPath();

		String viewName = "";

		switch (url) {
		case "/admin" : 
			viewName = adminLogin(request, response);
			break;
		
		
		  case "/login" : viewName = adminLoginSucess(request, response); break;
		 
		 
		case "/addproduct":
			viewName = showNewProductForm(request, response);
			break;
		case "/insertproduct":
			viewName = doCreateOrSaveProd(request, response);
			break;
		case "/listOfproductsAdmin":
			viewName =getListOfProducts(request, response);
			break;
		case "/editProd":
			viewName =doEditProduct(request, response);
			break;
		case "/saveproduct":
			viewName = doCreateOrSaveProd(request, response);
			break;
		case "/deleteProd":
			viewName =doDeleteProduct(request, response);
			break;
		case "/logout":
			viewName =logoutFromAdmin(request, response);
			break;
		
		}

		request.getRequestDispatcher(viewName).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
			
			ProductItem item = new ProductItem();
			request.setAttribute("productMaster", item);
			request.setAttribute("isNew", true);
			request.setAttribute("loginsuccess", true);
			
			return "newproduct.jsp";
			
		}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("loginsuccess", false);
		String view = "AdminUserLogin.jsp";

		return view;
	}
	
	
	  private String adminLoginSucess(HttpServletRequest request, HttpServletResponse
	  response) {
	  String view="";
	 
	  request.setAttribute("loginsuccess", true);
	  view="AdminUserLogin.jsp";
	  view="listOfproductsAdmin";
	  return view; 
	  
	  }
	  
	 
		private String doCreateOrSaveProd(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			
			ProductItem pm = new ProductItem(); 
			pm.setId(Integer.parseInt(request.getParameter("id")));
			pm.setProductDescription(request.getParameter("productDescription"));
			pm.setProductName(request.getParameter("productName"));
			pm.setCost(Double.parseDouble(request.getParameter("cost")));
			
			String view = "";
			
			try {
				
				
				if (request.getServletPath().equals("/insertproduct")) {
					System.out.println("in insert");
					adminService.validateAndAdd(pm);
				} else {
					adminService.validateAndSave(pm);
				}
				request.setAttribute("msg", "Product is saved");
				request.setAttribute("loginsuccess", true);
				view="AdminUserLogin.jsp";
				view="listOfproductsAdmin";
			} catch (CkException e) {
				request.setAttribute("errMsg", e.getMessage());
				view = "errorPage.jsp";
			}
		
			return view;
		}
		
		private String getListOfProducts(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String view = "";
		
			try {
				List<ProductItem> products = adminService.getAllProductMasters();
				request.setAttribute("productMaster", products);
				request.setAttribute("loginsuccess", true);
				view = "listproducts.jsp";
			} catch (CkException e) {
				request.setAttribute("errMsg", e.getMessage());
				view = "errorPage.jsp";
			}
		
			return view;
		}
		
		private String doEditProduct(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String view = "";
		
			int productMasterId = Integer.parseInt(request.getParameter("id"));
			System.out.println("productMasterId  " + productMasterId);
			try {
				ProductItem pm = adminService.getProductMaster(productMasterId);
				System.out.println("pm " +pm.getProductName());
				request.setAttribute("productMaster", pm);
				request.setAttribute("isNew", false);
				request.setAttribute("loginsuccess", true);
				view = "newproduct.jsp";
			} catch (CkException e) {
				request.setAttribute("errMsg", e.getMessage());
				view = "errorPage.jsp";
			}
		
			return view;
		}
		
		private String doDeleteProduct(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String view = "";
			Integer productMasterId = Integer.parseInt(request.getParameter("id"));
			try {
				adminService.deleteProductMaster(productMasterId);
				request.setAttribute("msg", "Product is deleted!");
				request.setAttribute("loginsuccess", true);
				view = "AdminUserLogin.jsp";
				view="listOfproductsAdmin";
			} catch (CkException e) {
				request.setAttribute("errMsg", e.getMessage());
				view = "errorPage.jsp";
			}
		
			return view;
		}
		
		private String logoutFromAdmin(HttpServletRequest request, HttpServletResponse response)
		{
			String view = "index.jsp";
			return view;
			
		}

}
