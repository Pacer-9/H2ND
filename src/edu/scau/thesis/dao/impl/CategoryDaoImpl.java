package edu.scau.thesis.dao.impl;

import org.springframework.stereotype.Repository;

import edu.scau.thesis.dao.CategoryDao;
import edu.scau.thesis.dao.base.BaseDao;
import edu.scau.thesis.model.Category;

@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao{


}
