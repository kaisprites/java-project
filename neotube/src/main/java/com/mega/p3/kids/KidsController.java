package com.mega.p3.kids;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
		List<KidsVONamed> bag = service.listByCategory(vo);
		model.addAttribute("bag", bag);
		return "list";
	}
	
	public List<KidsVO> listBySearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping("video")
	public String getVideo(String video_id, Model model) {
		KidsVO vo = new KidsVO();
		vo.setVideo_id(video_id);
		KidsVONamed video = service.one(vo);
		model.addAttribute("video", video);
		return "video";
	}

	public void upload(KidsVO vo) {
		// TODO Auto-generated method stub

	}

}
