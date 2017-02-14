package edu.scau.thesis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.scau.thesis.model.Category;
import edu.scau.thesis.model.Item;
import edu.scau.thesis.model.User;
import edu.scau.thesis.service.ItemService;
import edu.scau.thesis.service.UserService;

@Controller
@RequestMapping("/item")
public class ItemController {
	//排序方式
	private final String SORTORDER = "ASC";
	//排序类型
	private final String SORTTYPE = "public_date";
	
	@Resource(name="itemService")
	private ItemService itemService;
	@Resource(name="userService")
	private UserService userService;
	@Autowired
	private Properties applicationProps;
	/**
	 * 加载首页
	 * @return map(allItems ,所有未交易商品)
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Map listAllNoDeal_Item(){
		//String saveDir = applicationProps.getProperty("savePicUrl");
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<Item> items = itemService.getAllNoDeal_Items(SORTORDER,SORTTYPE);
		List<Category> categorys = itemService.getAllCategory();
		//map.put("picDir", saveDir);
		map.put("allItems", items);
		map.put("categorys", categorys);
		return map;
	}
	/**
	 * 获得分类商品
	 * @param categoryID 分类的序号
	 * @return map(subItems ,分类商品)
	 */
	@RequestMapping("/category/{categoryID}")
	@ResponseBody
	public Map listItemByCategory(@PathVariable String categoryID){
		//String saveDir = applicationProps.getProperty("savePicUrl");
		Map<String,Object> map = new HashMap<String,Object>();
		List<Item> items = null;
		if(categoryID.equals("-1")){
			items = (List<Item>) this.listAllNoDeal_Item().get("allItems");
		}
		else{
			items = (List<Item>) itemService.getItemsByCategory(categoryID,SORTORDER,SORTTYPE);
		}
		//map.put("picDir", saveDir);
		map.put("subItems", items);
		return map;
	}
	/**
	 * 按关键字搜索商品
	 * @param keyword 搜索的关键词
	 * @return
	 */
	@RequestMapping("/search/{keyword}")
	@ResponseBody
	public Map search(@PathVariable String keyword){
		//String saveDir = applicationProps.getProperty("savePicUrl");
		Map<String,Object> map = new HashMap<String,Object>();
		List<Item> items = null;
		if(StringUtils.isEmpty(keyword)){
			items = itemService.getAllNoDeal_Items(SORTORDER,SORTTYPE);
		}
		else{
			items = (List<Item>) itemService.getItemsByKeyword(keyword);
		}
		//map.put("picDir", saveDir);
		map.put("searchItems", items);
		return map;
	}
	/**
	 * 对商品进行排序
	 * @param categoryID 分类序号
	 * @param sortOrder	排序方式（升、降）
	 * @param sortType	排序类型
	 * @return
	 */
	@RequestMapping("/category/{categoryId}/order/{sortOrder}/{sortType}")
	@ResponseBody
	public Map order(@PathVariable String categoryId,@PathVariable String sortOrder,@PathVariable String sortType){
		//String saveDir = applicationProps.getProperty("savePicUrl");
		Map<String,Object> map = new HashMap<String,Object>();
		switch(sortType){
			case "人气": sortType = "focus_number";break;
			case "时间":sortType = "public_date";break;
			case "价格":sortType = "price";break;
			default : sortType = "public_date";
		}
		List<Item> items = null;
		if(categoryId.equals("-1")){
			items = itemService.getAllNoDeal_Items(sortOrder,sortType);
		}
		else{
			items = (List<Item>) itemService.getItemsByCategory(categoryId, sortOrder, sortType);
		}
		//map.put("picDir", saveDir);
		map.put("sortItems", items);
		return map;
	}
	/**
	 * 返回商品详细信息
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping("/detail/itemId/{itemId}")
	@ResponseBody
	public Map itemDetailMessage(@PathVariable String itemId){
		Map<String,Object> map = new HashMap<String,Object>();
		Item item = itemService.getItemById(itemId);
		User user = item.getUser();
		map.put("item", item);
		map.put("owner", user);
		return map;
	}
	public Properties getApplicationProps() {
		return applicationProps;
	}
	public void setApplicationProps(Properties applicationProps) {
		this.applicationProps = applicationProps;
	}
	
}
