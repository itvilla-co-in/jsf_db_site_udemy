package com.dev.user.model;

import java.io.Serializable;

public class Items implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String title_hr;
	private String title_en;
	private String title_de;
	private String text1_hr;
	private String text1_en;
	private String text1_de;
	private String text2_hr;
	private String text2_en;
	private String text2_de;
	private String text3_hr;
	private String text3_en;
	private String text3_de;
	private String front_page;
	private String categories_id;
	private String cat_title;
	private Double price;
	private Double price_discount;
	private String created_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle_hr() {
		return title_hr;
	}
	public void setTitle_hr(String title_hr) {
		this.title_hr = title_hr;
	}
	public String getTitle_en() {
		return title_en;
	}
	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}
	public String getTitle_de() {
		return title_de;
	}
	public void setTitle_de(String title_de) {
		this.title_de = title_de;
	}
	public String getText1_hr() {
		return text1_hr;
	}
	public void setText1_hr(String text1_hr) {
		this.text1_hr = text1_hr;
	}
	public String getText1_en() {
		return text1_en;
	}
	public void setText1_en(String text1_en) {
		this.text1_en = text1_en;
	}
	public String getText1_de() {
		return text1_de;
	}
	public void setText1_de(String text1_de) {
		this.text1_de = text1_de;
	}
	public String getText2_hr() {
		return text2_hr;
	}
	public void setText2_hr(String text2_hr) {
		this.text2_hr = text2_hr;
	}
	public String getText2_en() {
		return text2_en;
	}
	public void setText2_en(String text2_en) {
		this.text2_en = text2_en;
	}
	public String getText2_de() {
		return text2_de;
	}
	public void setText2_de(String text2_de) {
		this.text2_de = text2_de;
	}
	public String getText3_hr() {
		return text3_hr;
	}
	public void setText3_hr(String text3_hr) {
		this.text3_hr = text3_hr;
	}
	public String getText3_en() {
		return text3_en;
	}
	public void setText3_en(String text3_en) {
		this.text3_en = text3_en;
	}
	public String getText3_de() {
		return text3_de;
	}
	public void setText3_de(String text3_de) {
		this.text3_de = text3_de;
	}
	public String getFront_page() {
		return front_page;
	}
	public void setFront_page(String front_page) {
		this.front_page = front_page;
	}
	public String getCategories_id() {
		return categories_id;
	}
	public void setCategories_id(String categories_id) {
		this.categories_id = categories_id;
	}
	public String getCat_title() {
		return cat_title;
	}
	public void setCat_title(String cat_title) {
		this.cat_title = cat_title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPrice_discount() {
		return price_discount;
	}
	public void setPrice_discount(Double price_discount) {
		this.price_discount = price_discount;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
