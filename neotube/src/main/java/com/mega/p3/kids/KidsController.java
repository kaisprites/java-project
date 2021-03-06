package com.mega.p3.kids;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@RequestMapping("kids")
@Controller
public class KidsController {
	
	@Autowired
	KidsService service;
	
	//페이지 리다이렉터
	@RequestMapping("/home")
	public String home() {
		return "kids/home";
	}
	@RequestMapping("/popular")
	public String popular() {
		return "kids/popular";
	}
	@RequestMapping("/subscribelist")
	public String subscribelist() {
		return "kids/subscribelist";
	}
	@RequestMapping("/likelist")
	public String likelist() {
		return "kids/likelist";
	}
	@RequestMapping("/history")
	public String history() {
		return "kids/history";
	}
	
	//전체리스트 뽑아오기
	@RequestMapping("/list")
	public String listByCategory(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);
		List<KidsVO> bag = service.listByCategory(vo);
		model.addAttribute("bag", bag);
		return "kids/list";
	}
	//인기 기준 리스트 뽑아오기
	@RequestMapping("/listbypopular")
	public String listByPopular(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);
		List<KidsVO> bag = service.listByPopular(vo);
		model.addAttribute("bag", bag);
		return "kids/list";
	}
	
	//구독 기준 리스트 뽑아오기
	@RequestMapping("/listbysubscribe")
	public String listBySubscribe(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);;
		List<KidsVO> bag = service.listBySubscribe(vo);
		model.addAttribute("bag", bag);
		return "kids/list";
	}
	
	
	//좋아요 기준 리스트 뽑아오기
	@RequestMapping("/listbylike")
	public String listByLike(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);;
		List<KidsVO> bag = service.listByLike(vo);
		model.addAttribute("bag", bag);
		return "kids/list";
	}
	
	//시청이력기준 리스트 뽑아오기
	@RequestMapping("/listbyhistory")
	public String listByHistory(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 50);
		vo.setAmount(50);;
		List<KidsVO> bag = service.listByHistory(vo);
		model.addAttribute("bag", bag);
		return "kids/list";
	}
	
	//비디오 1개 정보 뽑아오기 
	@RequestMapping("/video")
	public String getVideo(String id, String user_id, Model model) {
		KidsVO vo = new KidsVO();
		vo.setVideo_id(id);
		KidsVO video = service.one(vo);
		model.addAttribute("video", video);
		
		String[] taglist = video.getTag().split(" ");
		model.addAttribute("taglist", taglist);
		
		return "kids/video";
	}
	
	//시청이력 추가하기
	@RequestMapping("/addhistory")
	public String addHistory(UserControlVO vo) {
		int result = service.addHistory(vo);
		return "kids/addhistory";
	}
	
	//좋아요 버튼
	//결과는 "do", "undo", "toggle" 중 하나이며 결과에 따라서 페이지의 동작이 다르다.
	@RequestMapping("/like")
	public String likeVideo(UserControlVO vo, Model model) {
		String result = service.likeVideo(vo); //"do", "undo", "toggle"
		model.addAttribute("result", result);
		return "kids/like";
	}
	
	//싫어요 버튼
	@RequestMapping("/dislike")
	public String dislikeVideo(UserControlVO vo, Model model) {
		String result = service.dislikeVideo(vo);
		model.addAttribute("result", result);
		return "kids/dislike";
	}
	
	//구독 버튼
	@RequestMapping(value="/subscribe", method=RequestMethod.POST)
	public String subscribe(UserControlVO vo, Model model) {
		String result = service.subscribe(vo); //"do", "undo"
		model.addAttribute("result", result);
		return "kids/subscribe";
	}
	
	//댓글 가져오기
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String getReply(KidsReplyVO vo, Model model) {
		List<KidsReplyVO> bag = service.getReply(vo);
		model.addAttribute("reply_bag", bag);
		return "kids/reply";
	}
	
	//댓글 쓰기
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String postReply(KidsReplyVO vo, Model model) {
		KidsReplyVO result = service.postReply(vo);
		model.addAttribute("replyvo", result);
		return "kids/submitreply";
	}
	
	//다음 동영상
	@RequestMapping("/nextvideo")
	public String nextVideoList(SearcherVO vo, int count, Model model) {
		vo.setStart(count * 20);
		vo.setAmount(20);
		List<KidsVO> bag = service.listByCategory(vo);
		model.addAttribute("bag", bag);
		return "kids/nextvideo";
	}

	@RequestMapping("/json")
	public String jsonTester(Model model) {		
		MongoClient mongoClient = MongoClients.create("mongodb+srv://root:1234@neotubekids.e68cc.mongodb.net/neotube?retryWrites=true&w=majority");
		MongoDatabase database = mongoClient.getDatabase("neotube");
		MongoCollection<Document> collection = database.getCollection("neotube");
		
		//insert
		Document insertData = new Document().append("k3", new Document().append("k4", "v4").append("k5", "v5"));
//		collection.insertOne(insertData);
		
		//replace
//		collection.replaceOne(Filters.eq("k3", insertData.get("k3")),
//				new Document().append("k6", new Document().append("k7", "v7")));
		
		//find
		List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
		model.addAttribute("json", foundDocument);
		
		mongoClient.close();
		return "kids/json";
	}
}
