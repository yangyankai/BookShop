package com.bookshop.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.mysql.jdbc.StringUtils;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class DBUtil {
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/test";
	private final static String username = "root";
	private final static String password = "123456";
	private static Connection conn;
	// 定义每页显示商品数量
	private static int span = 2;

	public static int getSpan() {
		return span;
	}

	public static void setSpan(int span) {
		DBUtil.span = span;
	}

	static {

		try {
			Class.forName(driver);
			getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	
	/*
	
	create table userinfor(
				 Uid    INT(11) not null AUTO_INCREMENT
				 Uname  varchar(50) not null 
				 Upwd  varchar(20) not null 
				 Uemail  varchar(100) default null
				 primary key (Uid))
	
	
	
	
	
	*/
//	// 创建用户表
//	public boolean createUserTable() {
//		String sql = "create table userinfor("
//				+ " Uid    INT(11) not null AUTO_INCREMENT,"
//				+ " Uname  varchar(50) not null ,"
//				+ " Upwd  varchar(20) not null ,"
//				+ " Uemail  varchar(100) default null ,"
//				+ " primary key (Uid))";
//
//		try {
//			Statement stmt = conn.createStatement();
//			stmt.executeQuery(sql);
//			return true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	// 执行查询
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	// 执行更新方法
	public int executeUpdate(String sql) {
		int result = 0;
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 关闭数据库连接
	public void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 根据sql语句获取页数
	public static int getTotalPages(String sql) {
		int totalpage = 1;
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			rs.next();
			int rows = rs.getInt(1);
			totalpage = rows / span;
			if (rows % span != 0) {
				totalpage++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeAll(con, st, rs);
		return totalpage;
	}

	// 获取当前页面中的数据
	public static ArrayList<String[]> getPageContent(int page, String sql) {
		ArrayList<String[]> lists = new ArrayList<String[]>();
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			java.sql.ResultSetMetaData rsmt = rs.getMetaData();
			int count = rsmt.getColumnCount();
			int start = (page - 1) * span;
			if (start != 0) {
				rs.absolute(start);
			}
			int temp = 0;
			while (rs.next() && temp < span) {
				temp++;

				String[] str = new String[count];
				for (int i = 0; i < str.length; i++) {
					str[i] = rs.getString(i + 1);
				}
				lists.add(str);

			}
			db.closeAll(con, st, rs);
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return lists;
	}

	// 根据sql语句获取查询到内容
	public static ArrayList<String[]> getInfoArr(String sql) {
		ArrayList<String[]> vtemp = new ArrayList<String[]>();
		try {
			DBUtil db = new DBUtil();
			Connection con = db.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmt = rs.getMetaData();
			int count = rsmt.getColumnCount();
			while (rs.next()) {
				String[] str = new String[count];
				for (int i = 0; i < count; i++) {
					str[i] = rs.getString(i + 1);
				}
				vtemp.add(str);
			}
			db.closeAll(con, st, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vtemp;
	}

	// 根据sql语句获取信息
	public static ArrayList<String> getInfo(String sql) {
		ArrayList<String> vclass = new ArrayList<String>();
		try {
			DBUtil db = new DBUtil();
			Connection con = db.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String str = rs.getString(1);
				vclass.add(str);
			}
			db.closeAll(con, st, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vclass;
	}

	// 根据sql语句判断要查询的对象是否是有效信息
	public static boolean isLegal(String sql) {
		boolean legal = false;
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);

			if (rs.next()) {
				legal = true;
			}
			db.closeAll(con, st, rs);
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return legal;
	}
}
