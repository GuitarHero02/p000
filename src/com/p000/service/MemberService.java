package com.p000.service;

import java.util.List;

import com.p000.model.Member;;

public interface MemberService {
	
	void add(Member member);
	
	void update(Member member);
	
	Member get(int id);
	
	List<Member> list();
	
	List<Member> listByName(String keyword);
	
	void delete(int id);

}
