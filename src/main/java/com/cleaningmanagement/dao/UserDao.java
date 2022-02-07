package com.cleaningmanagement.dao;

import java.util.List;

import com.cleaningmanagement.model.User;

public interface UserDao {
	public boolean insertUserDatabase(User user);
	public User validateUser(String email, String password);
	public int findUserId(User user);
	public User findUser(int id);
	public int findUser(String email);
	public  List<Object>  userBill(User user);
	public List<List<Object>> showbill(User user);
	public List<User> showUser();
	public boolean rechargeWallet(User user);
	public boolean updateWallet(User user, int amount);
	public boolean refundWallet(User user, int amount);
}
