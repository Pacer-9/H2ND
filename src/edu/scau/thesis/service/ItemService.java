package edu.scau.thesis.service;

import java.util.List;

import edu.scau.thesis.model.Category;
import edu.scau.thesis.model.Item;
import edu.scau.thesis.model.User;

public interface ItemService {
	public Item getItemById(String id);
	public List<Item> getItemsByKeyword(String keyword);
	public List<Item> getItemsByCategory(String categoryId,String sortOrder,String sortType);
	public List<Item> getItemsOfUserByStatement(String userid, String stmt,String sortOrder,String sortType);
	public List<Item> getAllNoDeal_Items(String sortOrder,String sortType);	
	public void publishItem(User user,Item item);
	public List<Category> getAllCategory();
	public Category getCategoryById(String CategoryId);
	public void offsaleItem(String id);
	public void resaleItem(String id);
	public void deleteItem(String id);
	public void saveOrUpdate(Item item);
}
