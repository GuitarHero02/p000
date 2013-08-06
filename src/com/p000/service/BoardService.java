package com.p000.service;

import java.util.List;

import org.springframework.ui.Model;

import com.p000.model.BoardVO;

public interface BoardService {

	List<BoardVO> list();
	
}
