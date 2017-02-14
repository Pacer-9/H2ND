package edu.scau.thesis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.scau.thesis.model.Category;
import edu.scau.thesis.model.Item;
import edu.scau.thesis.model.User;
import edu.scau.thesis.service.ItemService;
import edu.scau.thesis.service.UserService;

@Controller
@RequestMapping("/pc")
public class PersonalCenterController {
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="itemService")
	private ItemService itemService;
	private final String sortOrder = "DESC";
	private final String sortType  = "public_date";
	@RequestMapping("/user/info")	
	public String to_PersonalInformation_Page(HttpSession session,ModelMap modelMap){		
		try {
			User user = (User) session.getAttribute("currentUser");
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("errorMsg", "用户没有登录，无权访问");
			return "error/403";
		}
		return "page/pc-information";
	}
	@RequestMapping("/user/info/update")
	@ResponseBody
	public Map updateInfomation(User user,HttpSession session){
		Map<String,Object> map = new HashMap<String, Object>(); 
		User update_user = (User) session.getAttribute("currentUser");
		update_user.setUserName(user.getUserName());
		update_user.setTelephoneNumber(user.getTelephoneNumber());
		update_user.setSocialAccount(user.getSocialAccount());
		update_user.setProfile(user.getProfile());
		try {
			userService.updateUser(update_user);
		} catch (Exception e) {
			map.put("msg", "保存失败");
			e.printStackTrace();
			return map;
		}
		map.put("nickname", user.getUserName());
		map.put("msg", "保存成功");
		return map;
	}
	@RequestMapping("/goods/manage")
	public String to_GoodsManagemrnt_Page(HttpSession session,ModelMap modelMap){		
		try {
			User user = (User) session.getAttribute("currentUser");
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("errorMsg", "用户没有登录，无权访问");
			return "error/403";
		}
		return "page/pc-goods";
	}
	@RequestMapping("/goods/manage/load")
	@ResponseBody
	public Map loadGoods(HttpSession session){
		User user = (User) session.getAttribute("currentUser");
		Map<String,Object> map = new HashMap<String, Object>(); 
		List<Item> notTradedGoods = itemService.getItemsOfUserByStatement(user.getId(),"未交易",sortOrder,sortType);
		List<Item> tradedGoods = itemService.getItemsOfUserByStatement(user.getId(),"已交易",sortOrder,sortType);
		List<Item> offsaleGoods = itemService.getItemsOfUserByStatement(user.getId(),"已下架",sortOrder,sortType);
		map.put("notTraded", notTradedGoods);
		map.put("traded", tradedGoods);
		map.put("offsale", offsaleGoods);
		return map;
	}
	@RequestMapping("/goods/manage/offsale")
	@ResponseBody
	public Map offsale(String itemId){
		Map<String,Object> map = new HashMap<String, Object>();
		itemService.offsaleItem(itemId);
		map.put("msg", "下架成功");
		return map;
	}
	@RequestMapping("/goods/manage/edit/load")
	@ResponseBody
	public Map editMessageLoad(String itemId){
		Map<String,Object> map = new HashMap<String, Object>();
		List<Category> categorys = itemService.getAllCategory();
		Item item = itemService.getItemById(itemId);
		map.put("categorys", categorys);
		map.put("item", item);
		return map;
	}
	@RequestMapping("/goods/manage/edit/save")
	@ResponseBody
	public Map editSave(Item item,String itemId,String categoryId){
		Map<String,Object> map = new HashMap<String, Object>();
		Item itemModel = itemService.getItemById(itemId);
		Category category = itemService.getCategoryById(categoryId);
		itemModel.setItem_title(item.getItem_title());
		itemModel.setPrice(item.getPrice());
		itemModel.setLocation(item.getLocation());
		item.setCategory(category);
		itemModel.setTelephoneNumber(item.getTelephoneNumber());
		itemModel.setSocialAccount(item.getSocialAccount());
		itemModel.setItem_description(item.getItem_description());
		itemService.saveOrUpdate(itemModel);
		map.put("msg", "保存成功");
		return map;
	}
	@RequestMapping("/goods/manage/resale")
	@ResponseBody
	public Map resaleGood(String itemId){
		Map<String,Object> map = new HashMap<String, Object>();
		itemService.resaleItem(itemId);
		map.put("msg", "上架成功");
		return map;
	}
	@RequestMapping("/goods/manage/delete")
	@ResponseBody
	public Map deleteGood(String itemId){
		Map<String,Object> map = new HashMap<String, Object>();
		itemService.deleteItem(itemId);
		map.put("msg", "删除成功");
		return map;
	}
}
