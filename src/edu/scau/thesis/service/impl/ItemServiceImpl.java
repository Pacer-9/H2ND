package edu.scau.thesis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.scau.thesis.dao.impl.ItemDaoImpl;
import edu.scau.thesis.model.Item;
import edu.scau.thesis.service.ItemService;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService{
	@Resource(name="itemDao")
	private ItemDaoImpl itemDao;
	private final String ITEM_STATMENT = "未交易";

	@Override
	public Map getItemByUserId(String userid) {
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Item i where i.UserID=?"; 
		Object[] objs = new Object[1];
		objs[0] = userid;
		List<Item> items = itemDao.find(hql, objs);
		Item item = items.get(0);
		map.put("item",item);
		return map;
	}

	@Override
	public Map getItemsByKeyword(String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Item i where i.item_title like ? AND i.item_statement=?";
		Object[] objs = new Object[2];
		objs[0] = "%"+keyword+"%";
		objs[1] = ITEM_STATMENT;
		List<Item> items = itemDao.find(hql, objs);
		map.put("items", items);
		return map;
	}

	@Override
	public Map getItemsByCategory(int categoryId,String sortOrder,String sortType) {
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Item i where i.categoryID = ? AND i.item_statement=?"
				 	+ " ORDER BY i."+sortType+" "+sortOrder;
		Object[] objs = new Object[2];
		objs[0] = categoryId;
		objs[1] = ITEM_STATMENT;
		List<Item> items = itemDao.find(hql, objs);
		map.put("items", items);
		return map;
	}

	@Override
	public Map getAllItems() {
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Item";
		List<Item> items = itemDao.find(hql);
		map.put("items", items);
		return map;
	}

	@Override
	public Map getAllNoDeal_Items(String sortOrder,String sortType) {
		Map<String,Object> map = new HashMap<String,Object>();
		String hql = "from Item i where i.item_statement= ?"
					+ " ORDER BY i."+sortType+" "+sortOrder;;
		Object[] objs = new Object[1];
		objs[0] = ITEM_STATMENT;
		List<Item> items = itemDao.find(hql,objs);
		map.put("items", items);
		return map;
	}

}
