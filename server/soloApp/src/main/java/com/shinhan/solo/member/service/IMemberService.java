package com.shinhan.solo.member.service;

import java.util.List;

import com.shinhan.solo.member.Member;

public interface IMemberService {
	void memberRegister(Member member);
	Member memberSearch(Member member);
	Member memberModify(Member member);
	int memberRemove(Member member);
	List<Member> memberAllSearch();
}