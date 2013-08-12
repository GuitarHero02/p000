package com.p000.persistance;

import java.util.List;

import com.p000.model.BoardVO;

public interface BoardDAO {

	List<BoardVO> list();

	BoardVO view(int seq);

	void updateCnt(int seq);
	
	int delete(int seq);

	int update(BoardVO boardVO);

	void write(BoardVO boardVO);
}
