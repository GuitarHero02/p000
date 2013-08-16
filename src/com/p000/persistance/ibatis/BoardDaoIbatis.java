package com.p000.persistance.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.p000.model.BoardVO;
import com.p000.persistance.BoardDAO;
@Repository
public class BoardDaoIbatis implements BoardDAO {

	@Autowired SqlMapClientTemplate sqlMapClientTemplate;
	
	@SuppressWarnings("unchecked")
	public List<BoardVO> list() {
		return sqlMapClientTemplate.queryForList("Board.list");
	}

	@Override
	public BoardVO view(int seq) {
		return (BoardVO) sqlMapClientTemplate.queryForObject("Board.view", seq);
	}

	@Override
	public void updateCnt(int seq) {
		sqlMapClientTemplate.update("Board.updateCnt", seq);		
	}

	@Override
	public int delete(int seq) {
		System.out.println("삭제까지");
		return sqlMapClientTemplate.delete("Board.delete", seq);
	}

	@Override
	public int update(BoardVO boardVO) {
		return sqlMapClientTemplate.update("Board.update", boardVO);
	}

	@Override
	public void write(BoardVO boardVO) {
		sqlMapClientTemplate.update("Board.insert", boardVO);
	}

}
