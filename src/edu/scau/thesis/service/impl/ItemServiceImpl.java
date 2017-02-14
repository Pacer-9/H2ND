package edu.scau.thesis.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.scau.thesis.dao.CategoryDao;
import edu.scau.thesis.dao.ItemDao;
import edu.scau.thesis.model.Category;
import edu.scau.thesis.model.Item;
import edu.scau.thesis.model.User;
import edu.scau.thesis.service.ItemService;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService{
	@Resource(name="itemDao")
	private ItemDao itemDao;
	@Resource(name="categoryDao")
	private CategoryDao  categoryDao;
	private final String ITEM_STATMENT_NOTTRADED = "未交易";
	private final String ITEM_STATMENT_OFFSALE = "已下架";
	

	@Override
	public Item getItemById(String id) {
		String hql = "from Item i where i.id = :id";
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("id", id);
		Item item = itemDao.find(hql, map).get(0);
		return item;
	}

	@Override
	public List<Item> getItemsByKeyword(String keyword) {
		String hql = "from Item i where i.item_title like :title AND i.item_statement= :stmt";
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("title", "%"+keyword+"%");
		map.put("stmt", ITEM_STATMENT_NOTTRADED);
		List<Item> items = itemDao.find(hql, map);
		return items;
	}

	@Override
	public List<Item> getItemsByCategory(String categoryId,String sortOrder,String sortType) {
		String hql = "from Item i where i.category.id = :cateId AND i.item_statement= :stmt"
				 	+ " ORDER BY i."+sortType+" "+sortOrder;
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("cateId", categoryId);
		map.put("stmt", ITEM_STATMENT_NOTTRADED);
		List<Item> items = itemDao.find(hql, map);
		return items;
	}

	@Override
	public List<Item> getItemsOfUserByStatement(String userid,String stmt,String sortOrder,String sortType) {
		String hql = "from Item i where i.item_statement= :stmt and i.user.id = :userid"
				+ " ORDER BY i."+sortType+" "+sortOrder;
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("stmt", stmt);
		map.put("userid", userid);		
		List<Item> items = itemDao.find(hql,map);
		return items;
	}

	@Override
	public List<Item> getAllNoDeal_Items(String sortOrder,String sortType) {
		String hql = "from Item i where i.item_statement= :stmt"
					+ " ORDER BY i."+sortType+" "+sortOrder;
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("stmt", ITEM_STATMENT_NOTTRADED);
		List<Item> items = itemDao.find(hql,map);
		/*List<Item> items = getItemsByStatement(ITEM_STATMENT, sortOrder, sortType);*/
		return items;
	}

	@Override
	public void publishItem(User user, Item item) {
		item.setUser(user);
		itemDao.saveOrUpdate(item);
	}

	@Override
	public List<Category> getAllCategory() {
		String hql = "from Category";
		List<Category> categorys = categoryDao.find(hql);
		return categorys;
	}

	@Override
	public Category getCategoryById(String CategoryId) {
		String hql = "from Category c where c.id = :id";
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("id", CategoryId);
		Category category = categoryDao.find(hql, map).get(0);
		return category;
	}

	@Override
	public void offsaleItem(String id) {
		Item item = getItemById(id);
		item.setItem_statement(ITEM_STATMENT_OFFSALE);
		item.setOff_date(new Date());
		itemDao.saveOrUpdate(item);
	}
	@Override
	public void resaleItem(String id) {
		Item item = getItemById(id);
		item.setFocus_number(0);
		item.setItem_statement(ITEM_STATMENT_NOTTRADED);
		item.setPublic_date(new Date());
		item.setOff_date(null);
		itemDao.saveOrUpdate(item);
		
	}
	@Override
	public void deleteItem(String id) {
		Item item = getItemById(id);
		itemDao.delete(item);		
	}
	@Override
	public void saveOrUpdate(Item item) {
		itemDao.saveOrUpdate(item);		
	}





}
