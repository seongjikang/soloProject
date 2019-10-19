package com.shinhan.solo.user.service;

import java.util.List;

import com.shinhan.solo.user.User;

public interface IUserService {
	int userRegister(User user);
	User userSearch(String uuid);
	User userSearch(String name, String idNum);
	int userModify(String password, String idNum);
	int userRemove(String idNum);
	List<User> userAllSearch();
}
