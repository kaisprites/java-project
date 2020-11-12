package com.mega.p3.kids;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("kids")
@Controller
public class KidsController {
	
	@Autowired
	KidsService service;
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	@RequestMapping("/popular")
	public String popular() {
		return "popular";
	}
	@RequestMapping("/subscribed")
	public String subscribed() {
		return "subscribed";
	}
	@RequestMapping("/likelist")
	public String likelist() {
		return "likelist";
	}
	@RequestMapping("/history")
	public String history() {
		return "history";
	}
	
	@RequestMapping("/home")
	public String listByCategory(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);
		List<KidsVOWithChannel> bag = service.listByCategory(vo);
		model.addAttribute("bag", bag);
		return "list";
	}
	
	@RequestMapping("/likelist")
	public String listByLike(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);;
		List<KidsVOWithChannel> bag = service.listByLike(vo);
		model.addAttribute("bag", bag);
		return "list";
	}
	
	public List<KidsVO> listBySearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping("/video")
	public String getVideo(String id, String user_id, Model model) {
		KidsVO vo = new KidsVO();
		vo.setVideo_id(id);
		KidsVOWithChannel video = service.one(vo);
		model.addAttribute("video", video);
		
		String[] taglist = video.getTag().split(" ");
		model.addAttribute("taglist", taglist);
		
		return "video";
	}
	
	@RequestMapping("/like")
	public String likeVideo(UserControlVO vo, Model model) {
		String result = service.likeVideo(vo); //"do", "undo", "toggle"
		model.addAttribute("result", result);
		return "like";
	}
	
	@RequestMapping("/dislike")
	public String dislikeVideo(UserControlVO vo, Model model) {
		String result = service.dislikeVideo(vo);
		model.addAttribute("result", result);
		return "dislike";
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String getReply(ReplyVO vo, Model model) {
		List<ReplyVO> bag = service.getReply(vo);
		model.addAttribute("reply_bag", bag);
		return "reply";
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String postReply(ReplyVO vo, Model model) {
		if(vo.getContent() == "") return "contentlessreply";
		ReplyVO result = service.postReply(vo);
		model.addAttribute("replyvo", result);
		return "submitreply";
	}
	
	@RequestMapping("/nextvideo")
	public String nextVideoList(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 20);
		vo.setAmount(20);
		List<KidsVOWithChannel> bag = service.listByCategory(vo);
		model.addAttribute("bag", bag);
		return "nextvideo";
	}

	public void upload(KidsVO vo) {
		// TODO Auto-generated method stub

	}

}
