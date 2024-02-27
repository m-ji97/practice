package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PhonebookService;
import com.javaex.vo.PersonVo;

@Controller
public class PhonebookController {
	
	//필드
	@Autowired
	private PhonebookService phonebookService;
	//생성자
	//메소드gs
	//메소드일반
	
	//등록폼
	@RequestMapping(value = "/phone/writeform", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(@ModelAttribute PersonVo personvo) {
		System.out.println("PhonebookController.writeForm()");
		
		return "/WEB-INF/views/writeForm";
	}
	
	//등록
		@RequestMapping(value="/phone/write2", method= {RequestMethod.GET, RequestMethod.POST})
		public String write2(@ModelAttribute PersonVo personvo) {
			System.out.println("PhonebookController.write()");

			System.out.println(personvo.toString());

			phonebookService.exeWrite(personvo);

			//리스트로 리다이렉트
			return "redirect:/phone/list";
	}
	//리스트
	@RequestMapping(value="/phone/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhonebookController.list()");

		List<PersonVo> personList = phonebookService.exeList();

		model.addAttribute("pList", personList);

		return "list";
	
	}
	//수정폼
	@RequestMapping(value = "/phone/modifyForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(@RequestParam(value="no")int no, Model model) {
		System.out.println("PhonebookController.modifyForm()");
		
		PersonVo personvo = phonebookService.exeModifyForm(no);
		
		model.addAttribute("personVo", personvo);
		
		return "modifyForm";
	}
	//수정
	@RequestMapping(value="/phone/modify", method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute PersonVo personvo) {
		System.out.println("PhonebookController.modify()");
		
		phonebookService.exeModify(personvo);
		
		return "redirect:/phone/list";
	}
	//삭제
	@RequestMapping(value="/phone/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("PhonebookController.delete()");
		
		phonebookService.exeDelete(no);
		
		return "redirect:/phone/list";
	}
}