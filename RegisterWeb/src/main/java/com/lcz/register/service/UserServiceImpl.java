package com.lcz.register.service;
/**
 * 用户服务实现类
 */
import com.lcz.register.dao.UserDao;
import com.lcz.register.dao.UserDaoImpl;
import com.lcz.register.entity.User;
import com.lcz.register.utils.MailUtil;

public class UserServiceImpl implements UserService{
	
	//用户注册的方法
	public void register(User user) {
		//将数据存入到数据库中
		UserDao userDao=new UserDaoImpl();
		userDao.register(user);
		//调用发送方法，发送一封激活邮件
		new Thread(new MailUtil(user.getEmail(), user.getCode())).start();
	}
	
	//根据激活码查询用户的方法
	public User findByCode(String code) {
		UserDao userDao=new UserDaoImpl();
		return userDao.findByCode(code);
	}
	//激活成功
	public void update(User user) {
		UserDao userDao=new UserDaoImpl();
		userDao.update(user);
	}

}
