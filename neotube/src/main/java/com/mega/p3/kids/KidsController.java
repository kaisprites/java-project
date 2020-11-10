package com.mega.p3.kids;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KidsController {
	
	@Autowired
	KidsService service;
	
	@RequestMapping("list")
	public String listByCategory(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);
		List<KidsVOWithChannel> bag = service.listByCategory(vo);
		model.addAttribute("bag", bag);
		return "list";
	}
	
	public List<KidsVO> listBySearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping("video")
	public String getVideo(String id, String user_id, Model model) {
		KidsVO vo = new KidsVO();
		vo.setVideo_id(id);
		KidsVOWithChannel video = service.one(vo);
		model.addAttribute("video", video);
		
		String[] taglist = video.getTag().split(" ");
		model.addAttribute("taglist", taglist);
		
		return "video";
	}
	
	@RequestMapping("like")
	public String likeVideo(UserControlVO vo) {
		int result = service.likeVideo(vo);
		if(result == 1) return "like";
		else return "likefail";
	}
	
	@RequestMapping("dislike")
	public String dislikeVideo(UserControlVO vo) {
		int result = service.dislikeVideo(vo);
		if(result == 1) return "dislike";
		else return "likefail";
	}
	
	@RequestMapping(value="reply", method=RequestMethod.GET)
	public String getReply(ReplyVO vo, Model model) {
		List<ReplyVO> bag = service.getReply(vo);
		model.addAttribute("reply_bag", bag);
		return "reply";
	}
	
	@RequestMapping(value="reply", method=RequestMethod.POST)
	public String postReply(ReplyVO vo, Model model) {		
		service.postReply(vo);
		return "submitreply";
	}
	
	@RequestMapping("nextvideo")
	public String nextVideoList(Model model) {
		List<KidsVO> bag = service.nextVideoList();
		
		return null;
	}

	public void upload(KidsVO vo) {
		// TODO Auto-generated method stub

	}

}
