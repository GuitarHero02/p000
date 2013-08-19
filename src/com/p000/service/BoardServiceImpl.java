package com.p000.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.p000.model.BoardVO;
import com.p000.persistance.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> list() {
		return boardDAO.list();
	}

	@Override
	public BoardVO view(int seq) {
		boardDAO.updateCnt(seq);
		return boardDAO.view(seq);
	}

	@Override
	public void write(BoardVO boardVO) {
		boardDAO.write(boardVO);
	}

	@Override
	public void delete(int seq) {
		boardDAO.delete(seq);
	}

	@Override
	public void update(BoardVO boardVO) {
		boardDAO.update(boardVO);
	}

	@Override
	public List<BoardVO> listBySearch(String keyword) {
		return boardDAO.listBySearch(keyword);
	}

}
