package com.shinhan.solo.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.solo.member.Member;
import com.shinhan.solo.member.dao.MemberDao;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
	MemberDao dao;
	
	@Override
	public void memberRegister(Member member) {
		int result = dao.memberInsert(member);
		
		if (result == 0) {
			System.out.println("Join Fail!!");
		} else {
			System.out.println("Join Success!!");
		}
		
	}

	@Override
	public Member memberSearch(Member member) {
		
		Member mem = dao.memberSelect(member);
		
		if (mem == null) {
			System.out.println("Login Fail!!");
		} else {
			System.out.println("Login Success!!");
		}
		
		return mem;
	}

	@Override
	public Member memberModify(Member member) {
		
		int result = dao.memberUpdate(member);
		
		if(result == 0 ) {
			System.out.println("Modify Fail!!");
			return null;
		} else {
			System.out.println("Modify Success!!");
		}
		
		return member;
	}
	
	@Override
	public int memberRemove(Member member) {
		
		int result = dao.memberDelete(member);
		
		if(result == 0 ) {
			System.out.println("Remove Fail!!");
		} else {
			System.out.println("Remove Success!!");
		}
		
		return result;
	}

	@Override
	public List<Member> memberAllSearch() {
		List<Member> members = dao.memberAllSelect();
		
		if (members == null || members.size() == 0) {
			System.out.println("ListUp Fail!!");
		} else {
			System.out.println("ListUp Success!!");
		}
		
		return members;
	}
	
	

}