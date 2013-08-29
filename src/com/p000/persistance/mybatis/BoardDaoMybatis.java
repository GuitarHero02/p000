package com.p000.persistance.mybatis;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.p000.model.BoardVO;
import com.p000.persistance.BoardDAO;
@Repository
public class BoardDaoMybatis implements BoardDAO {

	@Autowired SqlSession sqlSession;
	
	@SuppressWarnings("unchecked")
	public List<BoardVO> list() {
		HashMap<String, String> input = new HashMap<String, String>();
		input.put("id", "yaic");
		return sqlSession.selectList("SqlSampleMapper.selectSample", input);
	}

	@Override
	public BoardVO view(int seq) {
		return (BoardVO) sqlSession.selectOne("Board.view", seq);
	}

	@Override
	public void updateCnt(int seq) {
		sqlSession.update("Board.updateCnt", seq);		
	}

	@Override
	public int delete(int seq) {
		System.out.println("삭제까지");
		return sqlSession.delete("Board.delete", seq);
	}

	@Override
	public int update(BoardVO boardVO) {
		return sqlSession.update("Board.update", boardVO);
	}

	@Override
	public void write(BoardVO boardVO) {
		sqlSession.update("Board.insert", boardVO);
	}

	@Override
	public List<BoardVO> listBySearch(String keyword) {
		return sqlSession.selectList("Board.listBySearch", keyword);
	}

}
