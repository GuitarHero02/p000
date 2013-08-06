package com.p000.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.p000.service.MemberService;

import com.p000.model.Member;

@Controller
@RequestMapping("/member")
@SessionAttributes("member")
public class MemberController {

	@Autowired MemberService memberService;
	
	@RequestMapping("/list")
	public void list(Model model){
		System.out.println("Start!!!!!!!!");
		model.addAttribute("list", memberService.list());
	}
	
	@RequestMapping("/{id}")
	public String view(@PathVariable int id, Model model){
		model.addAttribute("member", memberService.get(id));
		return "member/view";
	}
	
	@RequestMapping("/update/{id}")
	public String updateForm(@PathVariable int id, Model model){
		model.addAttribute("member", memberService.get(id));
		return "member/update";
	}
	
	@RequestMapping("/form")
	public void form(Model model){
		model.addAttribute("member", new com.p000.model.Member());
	}
	
	@RequestMapping(value="/search")
	public String search(@RequestParam String keyword, Model model){
		System.out.println("keyword : "+keyword);
		model.addAttribute("list", memberService.listByName(keyword));
		return "member/list";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String formSubmit(@Valid Member member, BindingResult result){
		if(result.hasErrors())
			return "member/form";
		memberService.add(member);
		return "redirect:/member/list";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(@Valid Member member, BindingResult result, SessionStatus status){
		if(result.hasErrors())
			return "member/update";
		memberService.update(member);
		status.setComplete();
		return "redirect:/member/list";
	}
	
}
