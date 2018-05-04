package com.lcz.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lcz.register.entity.User;
import com.lcz.register.utils.DbUtils;

public class UserDaoImpl implements UserDao{

	public void register(User user) {
		//注册用户的操作，将用户信息存入数据库中
		Connection conn = null;
		try {
			conn=DbUtils.getConnection();
			String sql ="insert into user(username,email,password,state,code) values(?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getState());
			ps.setString(5, user.getCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(conn);
		}
			
	}
	//根据激活码查询用户的方法
	public User findByCode(String code) {
		Connection conn = null;
		User user=null;
		try {
			conn=DbUtils.getConnection();
			String sql="select *from user where code=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();;
			user=new User();
			while(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setState(rs.getInt("state"));
				user.setCode(rs.getString("code"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询失败");
		}finally {
			DbUtils.close(conn);
		}
		return user;
		
	}
	
	//查找到激活码后，更新数据库中的表
	public void update(User user) {
		Connection conn = null;
		try {
			conn=DbUtils.getConnection();
			String sql="update user set username=?,password=?,email=?,state=?,code=? where id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getState());
			ps.setString(5, user.getCode());
			ps.setInt(6, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更新失败");
		}finally {
			DbUtils.close(conn);
		}
	}
}
