package com.p000.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.p000.model.BoardVO;
import com.p000.persistance.BoardDAO;

public class BoardServiceImpl implements BoardService {

	@Autowired BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> list() {
		return boardDAO.list();
	}

}
