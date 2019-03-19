package com.dev.user.config;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class Config {

	public String[] langs_arr = {"hr", "en", "de"};
	
	public boolean is_on_local_server(){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String server_name = request.getServerName();
		boolean is_local = false;
		
		is_local = server_name.equals("localhost") ? true : false;
		
		return is_local;
	
	}
	
	public String get_directory(Object type){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String server_name = request.getServerName();
		String location = "";
		
		if(type.equals("image")){
			location = server_name.equals("localhost") ? "myfolderpath_photos_local" : "myfolderpath_photos";
		} else if(type.equals("doc")){
			location = server_name.equals("localhost") ? "myfolderpath_files_local" : "myfolderpath_files";
		}
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		String my_dir = ctx.getExternalContext().getInitParameter(location);
		
		return my_dir;
	}
	
	public String get_site_path(Object type){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String server_name = request.getServerName();
		String location = "";
		
		if(type.equals("image")){
			location = server_name.equals("localhost") ? "mysitepath_photos_local" : "mysitepath_photos";
		} else if(type.equals("doc")){
			location = server_name.equals("localhost") ? "mysitepath_files_local" : "mysitepath_files";
		}
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		String my_dir = ctx.getExternalContext().getInitParameter(location);
		
		return my_dir;
	}
	
}
