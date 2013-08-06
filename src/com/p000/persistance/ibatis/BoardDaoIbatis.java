package com.p000.persistance.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.p000.model.BoardVO;
import com.p000.persistance.BoardDAO;

public class BoardDaoIbatis implements BoardDAO {

	@Autowired SqlMapClientTemplate sqlMapClientTemplate;
	@Override
	public List<BoardVO> list() {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("select * from CENTER_BOARD");
	}

}
