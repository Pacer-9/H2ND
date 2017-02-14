package edu.scau.thesis.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.scau.thesis.model.base.BasePOJO;

/**
 * 
 * @author pazezhe
 * 用户表
 */
@Entity
@Table(name="USER")
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User extends BasePOJO{
	private static final long serialVersionUID = 1L;
	//用户编号
	@Id@Column(name="USERID")
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	//用户账号
	@Column(name="U_ACCOUNT")
	private String userAccount;
	//密码
	@Column(name="PASSWORD")
	private String password;
	//用户名称（昵称）
	@Column(name="USERNAME")
	private String userName;
	//手机号码
	@Column(name="TELE_NUM")
	private String telephoneNumber;
	//社交账号（QQ、微信等等）
	@Column(name="S_ACCOUNT")
	private String socialAccount;
	//用户头像地址
	@Column(name="AVATAR")
	private String avatar;
	//个人简介
	@Column(name="PROFILE")
	private String profile;
	//注册时间
	@Column(name="R_DATE")
	private Date register_date;
	@OneToMany(cascade={CascadeType.ALL},mappedBy="user")	
	private Collection<Item> items = new ArrayList<Item>();
	@JsonIgnore
	public Collection<Item> getItems() {
		return items;
	}
	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	

}
