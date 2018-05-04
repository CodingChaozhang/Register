package com.lcz.register.service;

import com.lcz.register.entity.User;

public interface UserService {
	//注册用户的操作，将用户信息存入数据库中
	public void register(User user);
	//查找激活码，返回用户名
	public User findByCode(String code);
	//查找到激活码后，更新数据库中的表
	public void update(User user);

}
