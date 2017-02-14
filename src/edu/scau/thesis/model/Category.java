package edu.scau.thesis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.scau.thesis.model.base.BasePOJO;

/**
 * 
 * @author pazezhe
 * 商品类别表
 */
@Entity
@Table(name="category")
public class Category extends BasePOJO{
	@Id@Column(name="id")
	private String id;
	@Column(name="cate")
	private String categoryName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
