package edu.scau.thesis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.scau.thesis.model.Item;
import edu.scau.thesis.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	//排序方式
	private final String SORTORDER = "ASC";
	//排序类型
	private final String SORTTYPE = "public_date";
	
	@Resource(name="itemService")
	private ItemService itemService;
	
	
	/**
	 * 获得全部未交易的商品
	 * @return map(allItems ,所有未交易商品)
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Map listAllNoDeal_Item(){
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<Item> items = (List<Item>) itemService.getAllNoDeal_Items(SORTORDER,SORTTYPE).get("items");
		map.put("allItems", items);
		return map;
	}
	/**
	 * 获得分类商品
	 * @param categoryID 分类的序号
	 * @return map(subItems ,分类商品)
	 */
	@RequestMapping("/category/{categoryID}")
	@ResponseBody
	public Map listItemByCategory(@PathVariable int categoryID){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Item> items = null;
		if(categoryID==0){
			items = (List<Item>) this.listAllNoDeal_Item().get("allItems");
		}
		else{
			items = (List<Item>) itemService.getItemsByCategory(categoryID,SORTORDER,SORTTYPE).get("items");
		}
		map.put("subItems", items);
		return map;
	}
	/**
	 * 
	 * @param keyword 搜索的关键词
	 * @return
	 */
	@RequestMapping("/search/{keyword}")
	@ResponseBody
	public Map search(@PathVariable String keyword){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Item> items = null;
		if(StringUtils.isEmpty(keyword)){
			items = (List<Item>) itemService.getAllNoDeal_Items(SORTORDER,SORTTYPE).get("items");
		}
		else{
			items = (List<Item>) itemService.getItemsByKeyword(keyword).get("items");
		}
		map.put("searchItems", items);
		return map;
	}
	/**
	 * 
	 * @param categoryID 分类序号
	 * @param sortOrder	排序方式（升、降）
	 * @param sortType	排序类型
	 * @return
	 */
	@RequestMapping("/order/{categoryId}/{sortOrder}/{sortType}")
	@ResponseBody
	public Map order(@PathVariable int categoryId,@PathVariable String sortOrder,@PathVariable String sortType){
		Map<String,Object> map = new HashMap<String,Object>();
		switch(sortType){
			case "人气": sortType = "focus_number";break;
			case "时间":sortType = "public_date";break;
			case "价格":sortType = "price";break;
			default : sortType = "public_date";
		}
		List<Item> items = null;
		if(categoryId==0){
			items = (List<Item>) itemService.getAllNoDeal_Items(sortOrder, sortType).get("items");
		}
		else{
			items = (List<Item>) itemService.getItemsByCategory(categoryId, sortOrder, sortType).get("items");
		}
		map.put("sortItems", items);
		return map;
	}
}
