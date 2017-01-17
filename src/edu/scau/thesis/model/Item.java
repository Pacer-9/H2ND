package edu.scau.thesis.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import edu.scau.thesis.model.base.BasePOJO;

/**
 * 
 * @author pazezhe
 * 二手商品表
 */
@Entity
@Table(name="SECONDHAND_ITEM")
public class Item extends BasePOJO{
	@Id@Column(name="ID")
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	/*//商品id
	@Column(name="ITEMID")
	private String itemID;*/
	//商品标题
	@Column(name="TITLE")
	private String item_title;
	//商品价格
	@Column(name="PRICE")
	private double price;
	//商品描述
	@Column(name="DESCRIPTION")
	private String item_description;
	//商品拥有者的用户id
	@Column(name="USERID")
	private String UserID;
	//商品的类别id
	@Column(name="CATEGORYID")
	private int categoryID;
	//交易地点
	@Column(name="LOCO")
	private String location;
	//商品状态
	@Column(name="STATEMENT")
	private String item_statement;
	//商品发布日期
	@Column(name="P_DATE")
	private Date public_date;
	//关注数
	@Column(name="F_NUM")
	private int focus_number;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
/*	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}*/
	public String getItem_title() {
		return item_title;
	}
	public void setItem_title(String item_title) {
		this.item_title = item_title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getItem_statement() {
		return item_statement;
	}
	public void setItem_statement(String item_statement) {
		this.item_statement = item_statement;
	}
	public Date getPublic_date() {
		return public_date;
	}
	public void setPublic_date(Date public_date) {
		this.public_date = public_date;
	}
	public int getFocus_number() {
		return focus_number;
	}
	public void setFocus_number(int focus_number) {
		this.focus_number = focus_number;
	}

}
