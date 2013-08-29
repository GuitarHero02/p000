package com.p000.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.p000.model.Member;
import com.p000.persistance.MemberDao;

//@Service
//@Transactional
public abstract class MemberServiceImpl implements MemberService {
	
	/*@Autowired MemberDao dao;

	public void add(Member member) {
		member.setJoined(new Date());
		dao.add(member);
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public Member get(int id) {
		return dao.get(id);
	}

	public List<Member> list() {
		return dao.list();
	}

	public void update(Member member) {
		dao.update(member);
	}

	public List<Member> listByName(String keyword) {
		System.out.println(keyword + "  :  keyword in the service");
		return dao.listByName(keyword);
	}
	*/
}
