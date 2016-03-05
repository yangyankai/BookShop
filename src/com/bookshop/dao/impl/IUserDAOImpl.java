package com.bookshop.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bookshop.dao.IUserDAO;
import com.bookshop.domain.User;
import com.bookshop.util.DBUtil;

public class IUserDAOImpl implements IUserDAO {
	
	
	@Override
	public User findUser(String name, String pwd) {
		// TODO Auto-generated method stub
		String sql = "select Uid form UserInfor where Uname'" + name
				+ "'and Upwd='" + pwd + "'";
		User user = null;
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				user = new User(id, username, password, email);
			}

			db.closeAll(con, st, rs);
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return user;
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
	int row=0;
	int id = user.getUid();
	String username = user.getUname();
	String password = user.getUpwd();
	String email = user.getUemail();
	
			String sql = "insert userInfor(Uname,Upwd,Uemail)values('"+username+"','" + password + "','"+email+"')";

			DBUtil db = new DBUtil();
			Connection con = db.getConnection();
			Statement st = null;
			ResultSet rs = null;
			try {
				st = con.createStatement();
				row = st.executeUpdate(sql);
				db.closeAll(con, st, rs);
			} catch (SQLException e) {
				e.printStackTrace();

			}

			return row;

	}

	@Override
	public User findUser(String name) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql = "select Uid form UserInfor where Uname'" + name+"'";
		User user = null;
		DBUtil db = new DBUtil();
		Connection con = db.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				user = new User(id, username, password, email);
			}

			db.closeAll(con, st, rs);
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return user;
	}

	@Override
	public boolean isLegal(String sql) {
		// TODO Auto-generated method stub 
		return false;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
