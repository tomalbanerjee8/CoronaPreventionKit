package com.wf.corona.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/login")
public class LoginFilterAdmin implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		
		if("admin".equals((String)req.getParameter("loginid")) && "admin".equals((String)req.getParameter("password")))
		{
			filterChain.doFilter(req, res);
		}
		else
		{

			req.setAttribute("loginsuccess", false);
			req.setAttribute("msg", "Login Unsucessful! Invalid Username or Password");
			req.getRequestDispatcher("AdminUserLogin.jsp").forward(req, res);

		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
