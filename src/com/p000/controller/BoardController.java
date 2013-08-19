package com.p000.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.p000.model.BoardList;
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
	
	@RequestMapping("/search")
	public String search(@PathVariable String boardName, Model model, @RequestParam String keyword){
		System.out.println("controller in:::::: "+ keyword);
		model.addAttribute("list", boardService.listBySearch(keyword));
		return "/"+boardName + "/list";
	}
	
	@RequestMapping(value="/list.json", 
			method = RequestMethod.GET ,
			headers="Accept=application/json",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody BoardList listJSON(@PathVariable String boardName, Model model, @RequestParam String keyword){

		BoardList list = new BoardList();
		list.setBoardList(boardService.listBySearch(keyword));
		return list;
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
	
	// /board/delete/
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@PathVariable String boardName, @ModelAttribute BoardVO boardVO
			, @RequestParam String pwd, ModelMap model, SessionStatus sessionStatus) {
		System.out.println("delete param : " + boardVO.getSeq());
		if(boardVO.getPassword().equals(pwd)) {
			boardService.delete(boardVO.getSeq());
			return "redirect:/"+boardName + "/list";
		}
		
		model.addAttribute("msg", "비밀번호가 다릅니다.");
		return "/"+boardName + "/update";	
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
