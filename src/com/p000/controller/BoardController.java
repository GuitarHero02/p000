package com.p000.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import com.p000.model.BoardVO;
import com.p000.service.BoardService;

@Controller
@RequestMapping("/{boardName}")
@SessionAttributes("boardVO")
public class BoardController {
	
	@Autowired BoardService boardService;
	
	@RequestMapping("/list")
	public void list(@PathVariable String boardName, Model model){
		model.addAttribute("list", boardService.list());
	}
	
	// /board/view/1
	@RequestMapping(value="/view/{seq}")
	public String view(@PathVariable String boardName, @PathVariable int seq, Model model) {
		BoardVO boardVO = boardService.view(seq);
		model.addAttribute("boardVO", boardVO);
		return "/"+boardName + "/view";
	}
	
	// /board/write
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(@PathVariable String boardName, Model model) {
		model.addAttribute("boardVO", new BoardVO());
		return "/"+boardName + "/write";
	}
	
	// /board/write
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@PathVariable String boardName, @Valid @ModelAttribute BoardVO boardVO, BindingResult result) {
		if(result.hasErrors()) {
			return "/"+boardName + "/write";
		} else {
			boardService.write(boardVO);
			return "redirect:/"+boardName + "/list";
		}
	}
	
	// /board/delete/1
	@RequestMapping(value="/delete/{seq}")
	public String delete(@PathVariable String boardName, @PathVariable int seq) {
		boardService.delete(seq);
		return "redirect:/"+boardName + "/list";
	}
	
	// /board/edit/1
	@RequestMapping(value="/update/{seq}", method=RequestMethod.GET)
	public String edit(@PathVariable String boardName, @PathVariable int seq, Model model) {
		BoardVO boardVO = boardService.view(seq);
		model.addAttribute("boardVO", boardVO);
		return "/"+boardName + "/update";
	}
	
	// /board/edit
	@RequestMapping(value="/update/{seq}", method=RequestMethod.POST)
	public String edit(@PathVariable String boardName, @Valid @ModelAttribute BoardVO boardVO, BindingResult result, @RequestParam String pwd, ModelMap model, SessionStatus sessionStatus) {
		if(result.hasErrors()) {
			return "/"+boardName + "/update";
		} else {
			System.out.println("업데이트 :::" + pwd);
			if(boardVO.getPassword().equals(pwd)) {
				boardService.update(boardVO);
				sessionStatus.setComplete();
				return "redirect:/"+boardName + "/list";
			}

			model.addAttribute("msg", "비밀번호가 다릅니다.");
			return "/"+boardName + "/update";			
		}
	}
}
