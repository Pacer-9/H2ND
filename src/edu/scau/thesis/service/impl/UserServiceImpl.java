package edu.scau.thesis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.scau.thesis.dao.UserDao;
import edu.scau.thesis.model.User;
import edu.scau.thesis.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Resource(name="userDao")
	private UserDao userDao;
	@Override
	public Map addUser(User user) {
		Map<String,Object> map = new HashMap<String,Object>();
		userDao.saveOrUpdate(user);
		map.put("msg", "添加成功");
		return map;

	}
	@Override
	public User getUserById(String id) {
		String hql = "from User u where u.id = :id";
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("id", id);
		User user = userDao.find(hql, map).get(0);
		return user;
	}
	/**
	 * 判断用户账号是否存在
	 * @param 用户账号
	 */
	@Override
	public Boolean isExisted(String userAccount) {
		Boolean isExist = true;
		String hql = "from User u where u.userAccount = :u_acc";
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("u_acc", userAccount);
		try {
			List<User> users = userDao.find(hql, map);
			isExist = users.isEmpty();
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		return isExist;
	}
	@Override
	public void updateUser(User user) {
		userDao.saveOrUpdate(user);		
	}

}
