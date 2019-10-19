package com.shinhan.solo.user.dao;

import java.util.List;

import com.shinhan.solo.user.User;

public interface IUserDao {
	int userInsert(User user);
	User userSelect(String uuid); //회원 가입 여부 판별
	User userSelect(String name, String idNum); // 대출 서류 심사
	int userUpdate(String password, String idNum);
	int userDelete(String idNum);
	List<User> userAllSelect();
}