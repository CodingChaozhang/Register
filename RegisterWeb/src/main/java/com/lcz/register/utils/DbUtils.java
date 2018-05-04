package com.lcz.register.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 连接池版本的数据库连接管理工具类
 * 适合于并发场合
 * @author LvChaoZhang
 *
 */
public class DbUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static int initSize;
	private static int maxActive;
	private static BasicDataSource bs;
	static {
		//连接池
		bs=new BasicDataSource();
		Properties cfg=new Properties();
		try {
			InputStream in =DbUtils.class.getClassLoader().getResourceAsStream("db.properties");
			cfg.load(in);
			//初始化参数
			driver=cfg.getProperty("jdbc.driver");
			url=cfg.getProperty("jdbc.url");
			username=cfg.getProperty("jdbc.user");
			password=cfg.getProperty("jdbc.password");
			initSize=Integer.parseInt(cfg.getProperty("initSize"));
			maxActive=Integer.parseInt(cfg.getProperty("maxActive"));
			in.close();
			//初始化连接池
			bs.setDriverClassName(driver);
			bs.setUrl(url);
			bs.setUsername(username);
			bs.setPassword(password);
			bs.setInitialSize(initSize);
			bs.setMaxActive(maxActive);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() {
		try {
			//getConnection()从连接池中获取重用的连接，如果连接池满了，则等待，如果有连接归还，则获取重用的连接
			Connection conn = bs.getConnection();
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	public static void rollback(Connection conn) {
		if(conn!=null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void close(Connection conn) {
		if(conn!=null) {
			try {
				//将用过的连接归还到连接池中
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
