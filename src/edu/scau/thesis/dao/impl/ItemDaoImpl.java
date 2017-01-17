package edu.scau.thesis.dao.impl;

import org.springframework.stereotype.Repository;

import edu.scau.thesis.dao.ItemDao;
import edu.scau.thesis.dao.base.BaseDao;
import edu.scau.thesis.model.Item;
@Repository("itemDao")
public class ItemDaoImpl extends BaseDao<Item> implements ItemDao{

}
