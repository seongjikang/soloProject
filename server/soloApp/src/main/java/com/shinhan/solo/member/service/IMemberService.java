package com.shinhan.solo.member.service;

import com.shinhan.solo.member.Member;

public interface IMemberService {
	void memberRegister(Member member);
	void memberSearch(Member member);
	Member[] memberModify(Member member);
	void memberRemove(Member member);
}