package edu.scau.thesis.service;

import java.util.Map;

import edu.scau.thesis.model.User;

public interface UserService {
	public Map addUser(User user);
	public User getUserById(String id);
	public Boolean isExisted(String userAccount);
	public void updateUser(User user);
}
