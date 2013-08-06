package com.p000.persistance;

import java.util.List;

import com.p000.model.Member;

public interface MemberDao {
	
	void add(Member member);
	
	void update(Member member);
	
	Member get(int id);
	
	List<Member> list();
	
	void delete(int id);

	List<Member> listByName(String keyword);

}
