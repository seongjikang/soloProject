package com.shinhan.solo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.solo.member.Member;
import com.shinhan.solo.user.User;
import com.shinhan.solo.user.dao.UserDao;

@Service
public class UserService implements IUserService {

	@Autowired
	UserDao dao;
	
	@Override
	public int userRegister(User user) {
		int result = dao.userInsert(user);
		
		if (result == 0) {
			System.out.println("Register Fail!!");
		} else {
			System.out.println("Register Success!!");
		}
		
		return result;
	}

	@Override
	public User userSearch(String uuid) {
		User user = dao.userSelect(uuid);
		
		if (user == null) {
			System.out.println("Select Fail!!");
		} else {
			System.out.println("Select Success!!");
		}
		
		return user;
	}

	@Override
	public User userSearch(String name, String idNum) {
		User user = dao.userSelect(name, idNum);
		
		if (user == null) {
			System.out.println("Select Fail!!");
		} else {
			System.out.println("Select Success!!");
		}
		
		return user;
	}
	
	@Override
	public User userSearchForLogin(String uuid, String password) {
		User user = dao.userSelectForLogin(uuid, password);
		
		if (user == null) {
			System.out.println("Select Fail!!");
		} else {
			System.out.println("Select Success!!");
		}
		
		return user;
	}

	@Override
	public int userModify(String password, String idNum) {
		int result = dao.userUpdate(password, idNum);
		
		if(result == 0 ) {
			System.out.println("Modify Fail!!");
		} else {
			System.out.println("Modify Success!!");
		}
		
		return result;
	}

	@Override
	public int userRemove(String idNum) {
		int result = dao.userDelete(idNum);
		
		if(result == 0 ) {
			System.out.println("Remove Fail!!");
		} else {
			System.out.println("Remove Success!!");
		}
		
		return result;
	}

	@Override
	public List<User> userAllSearch() {
		List<User> users = dao.userAllSelect();
		
		if (users == null || users.size() == 0) {
			System.out.println("ListUp Fail!!");
		} else {
			System.out.println("ListUp Success!!");
		}
		
		return users;
	}

}
