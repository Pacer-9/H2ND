package edu.scau.thesis.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.scau.thesis.model.base.BasePOJO;

/**
 * 
 * @author pazezhe
 * 二手商品表
 */
@Entity
@Table(name="SECONDHAND_ITEM")
public class Item extends BasePOJO{
	private static final long serialVersionUID = 1L;
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
	//商品拥有者
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="USERID")
	private User user;
	//商品的类别id
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="CATEGORYID")
	private Category category;
	//交易地点
	@Column(name="LOCO")
	private String location;
	//商品状态
	@Column(name="STATEMENT")
	private String item_statement;
	//商品发布日期
	@Column(name="P_DATE")
	private Date public_date;
	//商品交易/下架日期
	@Column(name="o_date")
	private Date off_date;
	//关注数
	@Column(name="F_NUM")
	private int focus_number;
	//商品图片
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="item_id")
	private Collection<Picture> pictures = new ArrayList<Picture>();
	//手机号码
	@Column(name="TELE_NUM")
	private String telephoneNumber;
	//社交账号（QQ、微信等等）
	@Column(name="S_ACCOUNT")
	private String socialAccount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	@JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
	
	public Date getOff_date() {
		return off_date;
	}
	public void setOff_date(Date off_date) {
		this.off_date = off_date;
	}
	public int getFocus_number() {
		return focus_number;
	}
	public void setFocus_number(int focus_number) {
		this.focus_number = focus_number;
	}
	public Collection<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(Collection<Picture> pictures) {
		this.pictures = pictures;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getSocialAccount() {
		return socialAccount;
	}
	public void setSocialAccount(String socialAccount) {
		this.socialAccount = socialAccount;
	}
	
}
