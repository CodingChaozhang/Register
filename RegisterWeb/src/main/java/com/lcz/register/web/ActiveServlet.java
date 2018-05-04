package com.lcz.register.web;
/**
 * 激活邮件
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcz.register.entity.User;
import com.lcz.register.service.UserService;
import com.lcz.register.service.UserServiceImpl;



public class ActiveServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接受激活码
		String code=req.getParameter("code");
		//根据激活码查询用户
		UserService userService= new UserServiceImpl();
		User user = userService.findByCode(code);
		
		//已经查询到，修改用户的状态
		if(user!=null) {
			//已经查询到了,修改用户的状态
			user.setState(1);
			user.setCode(null);
			userService.update(user);
			req.setAttribute("msg", "你已经激活成功，请去登录！");
			req.getRequestDispatcher("/msg.jsp").forward(req, resp);
			
		}else {
			//根据激活码没有查询到该用户
			req.setAttribute("msg", "你的激活码有误，请重新激活");
			req.getRequestDispatcher("/msg.jsp").forward(req, resp);
		}
	}
	
}
