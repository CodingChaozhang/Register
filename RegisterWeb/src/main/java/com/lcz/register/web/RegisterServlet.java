package com.lcz.register.web;
/**
 * 注册账号,将账号存入数据库的表中
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcz.register.entity.User;
import com.lcz.register.service.UserService;
import com.lcz.register.service.UserServiceImpl;
import com.lcz.register.utils.CodeUtil;

public class RegisterServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接受数据
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		//封装数据
		User user=new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setState(0);//0:未激活  1:已激活
		String code=CodeUtil.generateUniqueCode()+CodeUtil.generateUniqueCode();
		user.setCode(code);
		//调用业务层处理数据
		UserService userService=new UserServiceImpl();
		userService.register(user);
		//页面跳转
		req.setAttribute("msg", "你已经注册成功!请去邮箱激活");
		req.getRequestDispatcher("/msg.jsp").forward(req, resp);
	}
	
}
