package com.shinhan.solo.member.dao;

import java.util.List;

import com.shinhan.solo.member.Member;

public interface IMemberDao {
	int memberInsert(Member member);
	Member memberSelect(Member member);
	int memberUpdate(Member member);
	int memberDelete(Member member);
	List<Member> memberAllSelect();
}