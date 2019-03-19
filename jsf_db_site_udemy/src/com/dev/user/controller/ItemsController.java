package com.dev.user.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.dev.user.config.Config;
import com.dev.user.config.LogIt;
import com.dev.user.dao.DatabaseManager;
import com.dev.user.model.Items;

@ManagedBean(name="item")
@ViewScoped
public class ItemsController extends Items {
	
	private static final long serialVersionUID = 1L;

	private Config conf;
	private DatabaseManager db;
	private Connection connection;
	
	private String sql;
	private String table;
	private int table_id;
	
	private String[] langs;
	private String[] titles = {"title_hr", "title_en", "title_de"};
	
	private long userID;
	
	private int success;
	private int current_cat_id;
	private LinkedHashMap<String, String> map;
	private String page_link;
	private LogIt lg;
	private List<ItemsController> data_view;
	
	public ItemsController(){
		
		conf = new Config();
		db = new DatabaseManager();
		connection = db.getConnection();
		
		langs = conf.langs_arr;
		
		this.table = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		this.table = this.table.replace("/", "");
		if(this.table.indexOf("_") > 0){
			this.table = this.table.substring(0,  this.table.indexOf("_"));
		} else {
			this.table = "items";
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		String sc = ec.getRequestParameterMap().get("success");
		if(sc == null){
			sc = "0";
		}
		setSuccess(Integer.parseInt(sc));
		
		String idc = (String) ec.getRequestParameterMap().get("id");
		if(idc == null){
			idc = "0";
		}
		setId(Integer.parseInt(idc));
		
		initialize();
		
		String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		setPage_link(viewId);
	}
	
	public ItemsController(int id, String title_hr, String cat_title, String created_date){
		setId(id);
		setTitle_hr(title_hr);
		setCat_title(cat_title);
		setCreated_date(created_date);
	}
	
	public void initialize(){
		
		if(getId() > 0){
			this.map = null;
			try {
				this.map = db.get_data(this.table, getId());
			} catch (SQLException e){
				e.printStackTrace();
			}
			
			
			String val = null;
			for(Map.Entry entry : this.map.entrySet()){
				
				if(entry.getKey().toString().equals("title_hr")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setTitle_hr(val);
				}
				if(entry.getKey().toString().equals("title_en")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setTitle_en(val);
				}
				if(entry.getKey().toString().equals("title_de")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setTitle_de(val);
				}
				
				if(entry.getKey().toString().equals("text1_hr")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setText1_hr(val);
				}
				if(entry.getKey().toString().equals("text1_en")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setText1_en(val);
				}
				if(entry.getKey().toString().equals("text1_de")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setText1_de(val);
				}
				
				if(entry.getKey().toString().equals("text2_hr")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setText2_hr(val);
				}
				if(entry.getKey().toString().equals("text2_en")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setText2_en(val);
				}
				if(entry.getKey().toString().equals("text2_de")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setText2_de(val);
				}
				
				if(entry.getKey().toString().equals("text3_hr")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setText3_hr(val);
				}
				if(entry.getKey().toString().equals("text3_en")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setText3_en(val);
				}
				if(entry.getKey().toString().equals("text3_de")){
					val = (entry.getValue() != null) ? entry.getValue().toString() : "";
					setText3_de(val);
				}
				
				if(entry.getKey().toString().equals("front_page")){
					setFront_page(entry.getValue().toString());
				}
				if(entry.getKey().toString().equals("categories_id")){
					setCurrent_cat_id(Integer.parseInt(entry.getValue().toString()));
				}
				if(entry.getKey().toString().equals("price")){
					setPrice(Double.parseDouble(entry.getValue().toString()));
				}
				if(entry.getKey().toString().equals("price")){
					setPrice_discount(Double.parseDouble(entry.getValue().toString()));
				}
			}
		
		}
		
	}
	
	public List<ItemsController> getItemListAll(){
		
		data_view = new ArrayList<ItemsController>();
		
		LinkedHashMap<String, Object> dat;
		dat = null;
		try {
			dat = this.db.get_data_all(this.table);
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		int j = 1;
		String item_id = null;
		String item_title = null;
		String item_cat_title = null;
		String item_created_date = null;
		for(Map.Entry entry : dat.entrySet()){
			if(j == 1){
				item_id = (entry.getKey().toString().contains("id_")) ? entry.getValue().toString() : "0";
			}
			if(j == 2){
				item_title = (entry.getKey().toString().contains("title_hr_")) ? entry.getValue().toString() : "";
			}
			if(j == 3){
				item_cat_title = (entry.getKey().toString().contains("cat_title_")) ? entry.getValue().toString() : "";
			}
			if(j == 4){
				item_created_date = (entry.getKey().toString().contains("created_date_")) ? entry.getValue().toString() : "01.01.2017. 00:00";
			}
			
			if(j == 4){
				data_view.add(new ItemsController(Integer.parseInt(item_id), item_title, item_cat_title, str_to_date(item_created_date)));
				j = 0;
			}
			j++;
		}
		
		return data_view;
	}
	
	public void deleteData(int table_id){
		try {
			this.db.delete_data(this.table, table_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int save(int redirect) throws SQLException, UnsupportedEncodingException{
		
		sql = "";
		String first = "";
		String key = "";
		String zar_dod;
		int n = 0;
		int n2 = 0;
		String uri = "";
		
		Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		for(Map.Entry entry : parameterMap.entrySet()){
			
			if( !entry.getKey().toString().contains("e_id") && 
				!entry.getKey().toString().contains("site_photos") && 
				!entry.getKey().toString().contains("file_table") && 
				!entry.getKey().toString().contains("file_title_") && 
				!entry.getKey().toString().contains("j_") && 
				!entry.getKey().toString().contains("javax.faces.ViewState"))
			{
				first = (n2 == 0) ? entry.getKey().toString() : first;
				if(n2 > 0){
					
					key = (entry.getKey().toString().contains(""+this.table+":")) ? entry.getKey().toString().replace(""+this.table+":", "") : entry.getKey().toString();
					if(!key.equals(this.table)){
						zar_dod = (n == 0) ? "" : ", ";
						sql += ""+zar_dod+""+key+"='"+entry.getValue()+"'";
						n++;
					}
				}
				n2++;
			}
			
			if( entry.getKey().toString().contains("j_") && (entry.getValue().equals("Save") || entry.getValue().equals("Save and list all")) ){
				uri = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI();
			}
			
		}
		
		LogIt.saveLog(conf.get_directory("doc")+"/my_log.log", "print of SQL: ", sql);
		table_id = db.save_items(this.table, getId(), sql);
		if(table_id <= 0){
			setSuccess(-1);
		}
		
		try {
			if(redirect == 1){
				uri = uri.replace("edit", "view");
			}
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri+"?id="+table_id+"&success=1");
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return table_id;
	}
	
	public String str_to_date(String dat){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
		Date date = null;
		String date_string = null;
		
		try {
			date = formatter.parse(dat);
			date_string = formatter.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date_string;
	}
	
	public String getTreeSelect(int curr_id) throws SQLException{
		OperationsController hp = new OperationsController();
		String sel = hp.display_tree_select(0, 1, curr_id);
		
		return sel;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String[] getLangs() {
		return langs;
	}

	public void setLangs(String[] langs) {
		this.langs = langs;
	}

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getCurrent_cat_id() {
		return current_cat_id;
	}

	public void setCurrent_cat_id(int current_cat_id) {
		this.current_cat_id = current_cat_id;
	}

	public LinkedHashMap<String, String> getMap() {
		return map;
	}

	public void setMap(LinkedHashMap<String, String> map) {
		this.map = map;
	}

	public String getPage_link() {
		return page_link;
	}

	public void setPage_link(String page_link) {
		this.page_link = page_link;
	}
}
