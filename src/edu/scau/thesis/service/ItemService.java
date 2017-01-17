package edu.scau.thesis.service;

import java.util.Map;

public interface ItemService {
	public Map getItemByUserId(String userId);
	public Map getItemsByKeyword(String keyword);
	public Map getItemsByCategory(int categoryId,String sortOrder,String sortType);
	public Map getAllItems();
	public Map getAllNoDeal_Items(String sortOrder,String sortType);

	

}
