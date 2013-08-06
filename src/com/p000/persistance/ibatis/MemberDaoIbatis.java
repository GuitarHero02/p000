package com.p000.persistance.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.p000.model.Member;
import com.p000.persistance.MemberDao;

@Repository /*hibernate설정시 주석처리해야 빈으로 등록되지 않음 */
public class MemberDaoIbatis implements MemberDao {
	
	@Autowired SqlMapClientTemplate sqlMapClientTemplate;
	
	public void add(Member member) {
		member.setId((int)System.currentTimeMillis());
		sqlMapClientTemplate.insert("Member.add", member);
	}

	public void delete(int id) {
		sqlMapClientTemplate.delete("Member.delete", id);
	}

	public Member get(int id) {
		return (Member) sqlMapClientTemplate.queryForObject("Member.get", id);
	}
	
	public void update(Member member) {
		sqlMapClientTemplate.update("Member.update", member);	
	}

	@SuppressWarnings("unchecked")
	public List<Member> list() {
		return sqlMapClientTemplate.queryForList("Member.list");
	}

	@Override
	public List<Member> listByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
