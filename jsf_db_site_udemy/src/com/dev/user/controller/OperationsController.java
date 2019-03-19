package com.dev.user.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import com.dev.user.config.Config;
import com.dev.user.dao.DatabaseManager;

import net.coobird.thumbnailator.Thumbnails;

@ManagedBean(name="operations")
@ViewScoped
public class OperationsController {

	private Config conf;
	private final String _LNG = "hr";
	private String t_categories;
	private String sql;
	private PreparedStatement ps;
	private DatabaseManager db;
	private Connection connection;
	
	private String content;
	
	private List<String> list_of_photos = new ArrayList<String>();
	private HashMap<String, String> map = new HashMap<String, String>();
	private List<HashMap<String, String>> list_of_images = new ArrayList<HashMap<String, String>>();
	
	private String photos_list;
	private long currentTimeInMillis;
	
	private int image_id;
	private String image_title;
	private int file_id;
	private String file_title;
	private String file_lng;
	
	private List<OperationsController> data_view;
	private List<OperationsController> data_view_2;
	
	private String sitepath_photos;
	private String sitepath_files;
	
	private int table_id;
	private String table;
	
	public OperationsController(){
		
		t_categories = "categories";
		
		ps = null;
		photos_list = "";
		
		conf = new Config();
		db = new DatabaseManager();
		connection = db.getConnection();
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		String idc = (String) ec.getRequestParameterMap().get("id");
		if(idc == null){
			idc = "0";
		}
		this.table_id = Integer.parseInt(idc);
		
		this.table = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		this.table = this.table.replace("/", "");
		this.table = this.table.substring(0, this.table.indexOf("_"));
		
		this.sitepath_photos = conf.get_site_path("image");
		this.sitepath_files = conf.get_site_path("doc");
	}
	
	public OperationsController(int image_id, String image_title){
		this.image_id = image_id;
		this.image_title = image_title;
	}
	
	public OperationsController(int file_id, String file_title, String file_lng){
		this.file_id = file_id;
		this.file_title = file_title;
		this.file_lng = file_lng;
	}
	
	public void handleFileUpload(FileUploadEvent event){
		
		this.data_view = new ArrayList<OperationsController>();
		this.data_view_2 = new ArrayList<OperationsController>();
		
		Object type = event.getComponent().getAttributes().get("type");
		Object table_id_obj = event.getComponent().getAttributes().get("table_id");
		int table_id = Integer.parseInt(table_id_obj.toString());
		
		String my_dir = conf.get_directory(type);
		
		try {
			File targetFolder = new File(my_dir);
			
			if(type.equals("image")){
				
				InputStream inputStream = event.getFile().getInputstream();
				OutputStream out = new FileOutputStream(new File(targetFolder, event.getFile().getFileName()));
				
				int read = 0;
				byte[] bytes = new byte[1024];
				while((read = inputStream.read(bytes)) != -1){
					out.write(bytes, 0, read);
				}
				inputStream.close();
				out.flush();
				out.close();
				
				Date date = new Date();
				long timeMili = date.getTime();
				String time_ms = String.valueOf(timeMili);
				
				String sizes = event.getComponent().getAttributes().get("sizes").toString();
				String[] sizes_arr = sizes.split(",");
				int j = 0;
				for(int i = 0; i < (sizes_arr.length/2); i++){
					
					String extension = "";
					int ext_pos = event.getFile().getFileName().lastIndexOf('.');
					if(ext_pos > 0){
						extension = event.getFile().getFileName().substring(ext_pos, event.getFile().getFileName().length());
					}
					String level_dod = (j == 2) ? "th_" : "";
					level_dod = (j > 2) ? "th_"+j+"_" : level_dod;
					
					Thumbnails.of(new File(my_dir+event.getFile().getFileName()))
					.size(Integer.parseInt(sizes_arr[j]), Integer.parseInt(sizes_arr[j+1]))
					.outputQuality(1.0)
					.toFile(new File(my_dir+level_dod+time_ms+extension));
					
					if(i == 0){
						int photos_id = save_photos(this.table, table_id, level_dod+time_ms+extension);
						this.data_view.add(new OperationsController(photos_id, "th_"+time_ms+extension+""));
					}
					
					j = j+2;
				}
				
				File f = new File(my_dir+event.getFile().getFileName());
				if(f.delete()){
					//System.out.println(f.getName() + " is deleted!");
				}
				
			} else if(type.equals("doc")){
				
				String lng = event.getComponent().getAttributes().get("lng").toString();
				
				Date date = new Date();
				long timeMili = date.getTime();
				String time_ms = String.valueOf(timeMili);
				
				String extension = "";
				int ext_pos = event.getFile().getFileName().lastIndexOf('.');
				if(ext_pos > 0){
					extension = event.getFile().getFileName().substring(ext_pos, event.getFile().getFileName().length());
				}
				
				InputStream inputStream = event.getFile().getInputstream();
				OutputStream out = new FileOutputStream(new File(targetFolder, time_ms+extension));
				
				int read = 0;
				byte[] bytes = new byte[1024];
				while((read = inputStream.read(bytes)) != -1){
					out.write(bytes, 0, read);
				}
				inputStream.close();
				out.flush();
				out.close();
				
				int files_id = save_files(this.table, table_id, time_ms+extension, lng);
				this.data_view_2.add(new OperationsController(files_id, time_ms+extension, lng));
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<OperationsController> get_photos(){
		
		ResultSet rs;
		this.data_view = new ArrayList<OperationsController>();
		
		try {
			sql = "SELECT id, photo_name from site_photos where table_name = '"+this.table+"' and table_id = ? order by orderby desc, id desc ";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, this.table_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				this.data_view.add(new OperationsController(rs.getInt("id"), "th_"+rs.getString("photo_name")+""));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.data_view;
	}
	
	public List<OperationsController> get_files(String doc_lng){
		
		ResultSet rs;
		this.data_view_2 = new ArrayList<OperationsController>();
		
		try {
			sql = "SELECT id, file_name from site_files where table_name = '"+this.table+"' and table_id = ? and lng = ? order by orderby desc, id desc ";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, this.table_id);
			ps.setString(2, doc_lng);
			rs = ps.executeQuery();
			
			while(rs.next()){
				this.data_view_2.add(new OperationsController(rs.getInt("id"), rs.getString("file_name"), doc_lng));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.data_view_2;
	}
	
	public int save_photos(String table, int table_id, String filename) throws SQLException{
		
		int key = 0;
		
		if(filename != ""){
			
			String sql = " insert into site_photos set orderby = 0, table_name = ?, table_id = ?, photo_name = ? ";
			ps = (PreparedStatement) connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, table);
			ps.setInt(2, table_id);
			ps.setString(3, filename);
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			key = keys.getInt(1);
			
			sql = " update site_photos set orderby = ? where id = ? ";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, key);
			ps.setInt(2, key);
			ps.executeUpdate();
		}
		
		return key;
	}
	
	public int save_files(String table, int table_id, String filename, String lng) throws SQLException{
		
		int key = 0;
		
		if(filename != ""){
			
			String sql = " insert into site_files set orderby = 0, table_name = ?, table_id = ?, file_name = ?, lng = ? ";
			ps = (PreparedStatement) connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, table);
			ps.setInt(2, table_id);
			ps.setString(3, filename);
			ps.setString(4, lng);
			ps.executeUpdate();
			
			ResultSet keys = ps.getGeneratedKeys();
			keys.next();
			key = keys.getInt(1);
			
			sql = " update site_files set orderby = ? where id = ? ";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, key);
			ps.setInt(2, key);
			ps.executeUpdate();
		}
		
		return key;
	}
	
	public void deleteImagesData(){
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int image_id = Integer.parseInt(params.get("image_id"));
		String image_title = params.get("image_title");
		
		try {
			this.db.delete_data("site_photos", image_id);
			
			String my_dir = conf.get_directory("image");
			
			File f = new File(my_dir+image_title);
			if(f.delete()){
				//System.out.println(my_dir+image_title + " is deleted!");
			}
			
			image_title = image_title.substring(3);
			f = new File(my_dir+image_title);
			if(f.delete()){
				//System.out.println(my_dir+image_title + " is deleted!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFilesData(){
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int file_id = Integer.parseInt(params.get("file_id"));
		String file_title = params.get("file_title");
		
		try {
			this.db.delete_data("site_files", file_id);
			
			String my_dir = conf.get_directory("doc");
			
			File f = new File(my_dir+file_title);
			if(f.delete()){
				//System.out.println(my_dir+file_title + " is deleted!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String repeat(String s, int n){
		
		if(s == null){
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++){
			sb.append(s);
		}
		return sb.toString();
	}
	
	public String display_tree_select(int parent, int level, int curr_id) throws SQLException{
		
		ResultSet rs;
		if(curr_id != (int)curr_id){
			curr_id = 0;
		}
		
		sql = "SELECT k.id as id, k.title_"+this._LNG+" as title FROM "+this.t_categories+" k WHERE k.parent_id = ? ";
		ps = (PreparedStatement) connection.prepareStatement(sql);
		ps.setInt(1, parent);
		rs = ps.executeQuery();
		while(rs.next()){
			
			String str = "&nbsp;&nbsp;";
			if(level > 1){
				str = repeat(str, level*2)+"|__";
			} else {
				str = "";
			}
			String sel = (curr_id == rs.getInt("id")) ? " selected=\"selected\"" : "";
			this.content += "<option value=\""+rs.getInt("id")+"\""+sel+">"+str+""+rs.getString("title")+"</option>";
			
			this.display_tree_select(rs.getInt("id"), level+1, curr_id);
		}
		
		return this.content;
	}
	
	
	public List<String> getList_of_photos() {
		return list_of_photos;
	}

	public void setList_of_photos(List<String> list_of_photos) {
		this.list_of_photos = list_of_photos;
	}

	public HashMap<String, String> getMap() {
		return map;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}

	public List<HashMap<String, String>> getList_of_images() {
		return list_of_images;
	}

	public void setList_of_images(List<HashMap<String, String>> list_of_images) {
		this.list_of_images = list_of_images;
	}

	public String getPhotos_list() {
		return photos_list;
	}

	public void setPhotos_list(String photos_list) {
		this.photos_list = photos_list;
	}

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public String getImage_title() {
		return image_title;
	}

	public void setImage_title(String image_title) {
		this.image_title = image_title;
	}

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getFile_lng() {
		return file_lng;
	}

	public void setFile_lng(String file_lng) {
		this.file_lng = file_lng;
	}

	public List<OperationsController> getData_view() {
		return data_view;
	}

	public void setData_view(List<OperationsController> data_view) {
		this.data_view = data_view;
	}

	public List<OperationsController> getData_view_2() {
		return data_view_2;
	}

	public void setData_view_2(List<OperationsController> data_view_2) {
		this.data_view_2 = data_view_2;
	}

	public String getSitepath_photos() {
		return sitepath_photos;
	}

	public void setSitepath_photos(String sitepath_photos) {
		this.sitepath_photos = sitepath_photos;
	}

	public String getSitepath_files() {
		return sitepath_files;
	}

	public void setSitepath_files(String sitepath_files) {
		this.sitepath_files = sitepath_files;
	}

	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}

	public long getCurrentTimeInMillis() {
		return System.currentTimeMillis();
	}

	public void setCurrentTimeInMillis(long currentTimeInMillis) {
		this.currentTimeInMillis = currentTimeInMillis;
	}
	
}
