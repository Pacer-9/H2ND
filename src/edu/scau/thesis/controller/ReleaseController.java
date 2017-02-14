package edu.scau.thesis.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.scau.thesis.model.Category;
import edu.scau.thesis.model.Item;
import edu.scau.thesis.model.Picture;
import edu.scau.thesis.model.User;
import edu.scau.thesis.service.ItemService;
import edu.scau.thesis.service.UserService;
import edu.scau.thesis.utils.FileUtils;

@Controller
@RequestMapping("/release")
public class ReleaseController {
	@Resource(name="itemService")
	private ItemService itemService;
	@Resource(name="userService")
	private UserService userService;
	@Autowired
	private FileUtils fileUtil; 
	@RequestMapping("/sale")
	public String releasepage(){
		return "goods-release";
	}
	
	/**
	 * 初始化发布表单内容
	 * @param session
	 * @return
	 */
	@RequestMapping("/init")
	@ResponseBody
	public Map getCategory(HttpSession session){
		User user = (User) session.getAttribute("currentUser");
		/*String userid = (String) session.getAttribute("userid");
		User user = userService.getUserById(userid);*/
		Map<String,Object> map = new HashMap<String,Object>(); 
		List<Category> categorys = itemService.getAllCategory();
		map.put("tel", user.getTelephoneNumber());
		map.put("s_account",user.getSocialAccount());
		map.put("categorys", categorys);
		return map;
	};
	/**
	 * 发布商品
	 * @param categoryId 分类编号
	 * @param item	商品
	 * @return
	 */
	@RequestMapping("/submit")
	public String publishItem(Item item,String categoryId,@RequestParam(value="files",required=false)MultipartFile[] files,HttpSession session){
		User user = (User) session.getAttribute("currentUser");
		/*User user = userService.getUserById(userid);*/
		item.setItem_statement("未交易");
		item.setPublic_date(new Date());
		item.setFocus_number(0);
		//分类
		Category category = itemService.getCategoryById(categoryId);
		item.setCategory(category);
		//图片
		if(files!=null){
			List<Picture> pictures = new ArrayList<Picture>();
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				Picture picture = new Picture();
				String newFileName = new Date().getTime()+"M"+(int)(Math.random()*1000)+".jpg";
				//String pictureSavePath = "";
				//存放图片
				try {
					fileUtil.saveFile(newFileName, file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				picture.setSequenceNumber(i+1);
				picture.setPictureUrl(newFileName);
				pictures.add(picture);
			}
			item.setPictures(pictures);
		}
		itemService.publishItem(user, item);
		return "result/release-success";
	}
}
