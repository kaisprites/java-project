package com.mega.p3.kids;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KidsController {
	
	@Autowired
	KidsService service;
	
	@RequestMapping("list")
	public String listByCategory(String category, Model model) {
		KidsVO vo = new KidsVO();
		vo.setCategory(category);
		List<KidsVOWithChannel> bag = service.listByCategory(vo);
		model.addAttribute("bag", bag);
		return "list";
	}
	
	public List<KidsVO> listBySearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping("video")
	public String getVideo(String id, Model model) {
		KidsVO vo = new KidsVO();
		vo.setVideo_id(id);
		KidsVOWithChannel video = service.one(vo);
		model.addAttribute("video", video);
		String[] taglist = video.getTag().split(" ");
		model.addAttribute("taglist", taglist);
		return "video";
	}

	public void upload(KidsVO vo) {
		// TODO Auto-generated method stub

	}

}
