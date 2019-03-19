package com.dev.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import com.dev.user.config.Config;

public class DatabaseManager {
	
	private Config conf;
	private boolean is_local;
	private Connection connection;
	private PreparedStatement ps;
	private Statement st;
	private String sql;
	
	private static String _JDBC_DRIVER;
	private static String _USERNAME;
	private static String _PASSWORD;
	private String _DATABASE;
	private String _CONNECTION_STRING;
	
	public DatabaseManager(){ // constructor
		
		connection = null;
		sql = null;
		
		conf = new Config();
		this.is_local = conf.is_on_local_server();
		_JDBC_DRIVER = "com.mysql.jdbc.Driver";
		
		if(this.is_local == false) { // production data
			_USERNAME = "";
			_PASSWORD = "";
			_DATABASE = "";
		} else {
			_USERNAME = "root";
			_PASSWORD = "";
			_DATABASE = "jsf_database";
		}
		
		_CONNECTION_STRING = "jdbc:mysql://localhost:3306/"+_DATABASE+"?useUnicode=true&characterEncoding=UTF-8";
		
		try {
			Class.forName(_JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("There is no JDBC driver for MySQL on server or your computer.");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		
		connection = null;
		
		try {
			connection = DriverManager.getConnection(_CONNECTION_STRING, _USERNAME, _PASSWORD);
		} catch (SQLException e){
			System.out.println("Connection has not been established.");
		} finally {
			if(connection != null){
				//System.out.println("Page refresh probably occured, connection to the database is alive...");
			} else {
				System.out.println("The connection to the database has not been established: Make sure your database is created.");
			}
		}
		
		return connection;
	}
	
	public int save_items(String table, int table_id, String sql) throws SQLException{
		
		String table_dod = "";
		String sql_dod = "";
		String sql_dod2 = "";
		
		if(table_id == 0){
			table_dod = "insert into";
			sql_dod = "";
			sql_dod2 = ", created = now() ";
		} else {
			table_dod = "update";
			sql_dod = "where id = '"+table_id+"' ";
			sql_dod2 = "";
		}
		
		sql = ""+table_dod+" "+table+" set "+sql+sql_dod+sql_dod2+" ";
		System.out.println("Data for save into database: "+sql);
		st = connection.createStatement();
		st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		
		int key = 0;
		if(table_id == 0){
			ResultSet keys = st.getGeneratedKeys();
			keys.next();
			key = keys.getInt(1);
		} else {
			key = table_id;
		}
		
		sql = " update "+table+" set orderby = ? where id = ? ";
		ps = (PreparedStatement) connection.prepareStatement(sql);
		ps.setInt(1, key);
		ps.setInt(2, key);
		ps.executeUpdate();
		
		return key;
	}
	
	public LinkedHashMap<String, String> get_data(String table, int table_id) throws SQLException {
		
		ResultSet rs;
		
		LinkedHashMap<String, String> map = new LinkedHashMap();
		sql = "SELECT * FROM "+table+" where id = ? ";
		System.out.println(sql);
		ps = (PreparedStatement) connection.prepareStatement(sql);
		ps.setInt(1, table_id);
		rs = ps.executeQuery();
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int count = rsMetaData.getColumnCount();
		String cnt;
		int m = 0;
		while(rs.next()){
			for(int i = 1; i<= count; i++){
				String val = rs.getString(i);
				map.put(rsMetaData.getColumnLabel(i), val);
			}
			m++;
		}
		cnt = ""+m;
		map.put("count", cnt);
		
		return map;
	}
	
	public LinkedHashMap<String, String> get_uploaded_data(String table, int table_id, String table2) throws SQLException {
		
		ResultSet rs;
		
		LinkedHashMap<String, String> map = new LinkedHashMap();
		sql = "SELECT * FROM "+table2+" where table_name = '"+table+"' and table_id = ? ";
		System.out.println(sql);
		ps = (PreparedStatement) connection.prepareStatement(sql);
		ps.setInt(1, table_id);
		rs = ps.executeQuery();
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int count = rsMetaData.getColumnCount();
		String cnt;
		int m = 0;
		while (rs.next()){
			for (int i = 1; i <=count; i++){
				String val = rs.getString(i);
				map.put(rsMetaData.getColumnLabel(i)+"_"+rs.getString(1), val);
			}
			m++;
		}
		cnt = ""+m;
		map.put("count", cnt);
		
		return map;
	}
	
	public LinkedHashMap<String, Object> get_data_all(String table) throws SQLException {
		
		ResultSet rs;
		
		LinkedHashMap<String, Object> map = new LinkedHashMap();
		sql = "SELECT *, DATE_FORMAT(created, '%d.%m.%Y. %H:%m') as created2 FROM "+table+" order by id desc ";
		ps = (PreparedStatement) connection.prepareStatement(sql);
		rs = ps.executeQuery();
		
		String cat_title = null;
		int j = 1;
		while(rs.next()){
			
			sql = "SELECT title_hr FROM categories where id = ? order by id desc";
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, rs.getInt("categories_id"));
			ResultSet rs2 = ps.executeQuery();
			
			cat_title = "";
			if(rs2.next()){
				cat_title = rs2.getString("title_hr");
			}
			map.put("id_"+j, Integer.toString(rs.getInt("id")));
			map.put("title_hr_"+j, rs.getString("title_hr"));
			map.put("cat_title_"+j, cat_title);
			map.put("created_date_"+j, rs.getString("created2"));
			j++;
		}
		
		return map;
	}
	
	public boolean delete_data(String table, int table_id) throws SQLException {
		
		sql = "DELETE FROM "+table+" where id = ? ";
		ps = (PreparedStatement) connection.prepareStatement(sql);
		ps.setInt(1, table_id);
		ps.executeUpdate();
		
		return true;
		
	}
}
