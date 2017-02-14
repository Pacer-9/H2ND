package edu.scau.thesis.dao.impl;

import org.springframework.stereotype.Repository;

import edu.scau.thesis.dao.UserDao;
import edu.scau.thesis.dao.base.BaseDao;
import edu.scau.thesis.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDao<User> implements UserDao{

}
