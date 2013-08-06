package com.p000.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p000.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired BoardService boardService;
	
	@RequestMapping("/list")
	public void list(Model model){
		model.addAttribute("list", boardService.list());
	}
}
