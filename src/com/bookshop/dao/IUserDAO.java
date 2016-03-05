package com.bookshop.dao;

import com.bookshop.domain.User;

public interface IUserDAO {

public User findUser(String name,String pwd);

public int addUser(User user);

public User findUser(String name);

public boolean isLegal(String sql);

public int getId();

public int updateUser(User user);
}
