package com.shinhan.solo.member.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.shinhan.solo.member.Member;

//@Component
@Repository
public class MemberDao implements IMemberDao{

	private HashMap<String, Member> dbMap;
	
	public MemberDao() {
		dbMap = new HashMap<String, Member>();
	}
	
	@Override
	public void memberInsert(String memId, String memPw, String memMail, String memPhone1, String memPhone2,
			String memPhone3) {
		System.out.println("memberRegister()");
		System.out.println("memId : " + memId);
		System.out.println("memPw : " + memPw );
		System.out.println("memMail : " + memMail);
		System.out.println("memPhone : " + memPhone1 + " - " + memPhone2 + " - " + memPhone3);
		
		Member member =new Member();
		member.setMemId(memId);
		member.setMemPw(memPw);
		member.setMemMail(memMail);
		member.setMemPhone1(memPhone1);
		member.setMemPhone2(memPhone2);
		member.setMemPhone3(memPhone3);
		
		dbMap.put(memId, member);
		
		Set<String> keys = dbMap.keySet();
		Iterator<String> iterator = keys.iterator();
		
		while(iterator.hasNext()) {
			String key = iterator.next();
			Member mem = dbMap.get(key);
			System.out.print("memberId : " + mem.getMemId() + "\t");
			System.out.print("|memberPw : " + mem.getMemPw() + "\t");
			System.out.print("|memberMail : " + mem.getMemMail() + "\t");
			System.out.print("|memberPhone : " + mem.getMemPhone1() + " - " + mem.getMemPhone2() + " - " + mem.getMemPhone3() + "\n");			
		}
	}

	@Override
	public Member memberSelect(String memId, String memPw) {
		Member member = dbMap.get(memId);
		
		return member;
	}

	@Override
	public void memberUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void membeDelete() {
		// TODO Auto-generated method stub
		
	}
	
}
