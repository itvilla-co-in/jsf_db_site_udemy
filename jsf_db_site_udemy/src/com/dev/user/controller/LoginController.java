package com.dev.user.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.user.model.Login;

@ManagedBean(name="auth")
@SessionScoped
public class LoginController extends Login implements Filter {
	
	private static final long serialVersionUID = 1L;

	private boolean loggedIn;
	private String success_status = null;

	private static final String[] users = {"admin:test", "kate:123456"};
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// get the login bean from session attribute
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		LoginController loginBean = (LoginController) req.getSession().getAttribute("auth");
		String url = req.getRequestURI();
		
		// for the first application request there is no loginBean in the session so user needs to log in
		// for other requests loginBean is presen but we need to check if the user has logged in successfully
		String contextPath = ((HttpServletRequest)request).getContextPath();
		if(loginBean == null || !loginBean.isLoggedIn()){
			if((url.endsWith("/")) || (url.contains("index.xhtml")) || (url.contains("_edit")) || (url.contains("_view")) ){
				((HttpServletResponse)response).sendRedirect(contextPath + "/login.xhtml");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			if(url.contains("login.xhtml")){
				((HttpServletResponse)response).sendRedirect(contextPath + "/index.xhtml");
			} else {
				chain.doFilter(request, response);
			}
		}
	}
	
	@Override
	public void destroy() {
		//
	}
	
	public static boolean isPostback(){
		return FacesContext.getCurrentInstance().isPostback();
	}
	
	public String doLogin(){
		// get every user from out sample data-store
		for(String user : users){
			String dbUsername = user.split(":")[0];
			String dbPassword = user.split(":")[1];
			
			// successfull login
			if(dbUsername.equals(getUser_name()) && dbPassword.equals(getPassword())){
				setLoggedIn(true);
				setSuccess_status("success");
				
				return "index";
			}
		}
		setSuccess_status("error");
		
		return "login";
	}
	
	public String logout(){
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "login.xhtml";
		
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getSuccess_status() {
		return success_status;
	}

	public void setSuccess_status(String success_status) {
		this.success_status = success_status;
	}

}
