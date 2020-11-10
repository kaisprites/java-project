package com.mega.p3.kids;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KidsService {
	
	@Autowired
	KidsDAO dao;

	public KidsVOWithChannel one(KidsVO kidsVO) {
		return dao.one(kidsVO);
	}

	public List<KidsVOWithChannel> listByCategory(SearcherVO vo) {
		return dao.listByCategory(vo);
	}

	public List<KidsVO> listBySearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public void upload(KidsVO vo) {
		// TODO Auto-generated method stub

	}
	
	public Integer getLike(UserControlVO vo) {
		return dao.getLike(vo);
	}
	
	public int likeVideo(UserControlVO vo) {
		Integer like = dao.getLike(vo);	//검색을 못할 경우 받아오는 null을 처리하기 위한 wrapper class
		if (like != null) {
			if(like.intValue() == 1) {
				dao.likeCancel(vo);
				dao.videoLikeUndo(vo);
				return 1;
			}
			else if(like.intValue() == 2) {
				dao.likeCancel(vo);
				dao.videoDislikeUndo(vo);
			}
		}
		dao.userLike(vo);
		dao.videoLike(vo);
		return 1;
	}
	
	public int dislikeVideo(UserControlVO vo) {
		Integer like = dao.getLike(vo);
		if (like != null) {
			if(like.intValue() == 2) {
				dao.likeCancel(vo);
				dao.videoDislikeUndo(vo);
				return 1;
			}
			else if(like.intValue() == 1) {
				dao.likeCancel(vo);
				dao.videoLikeUndo(vo);
			}
		}
		dao.userDislike(vo);
		dao.videoDislike(vo);
		return 1;
	}

	public List<ReplyVO> getReply(ReplyVO vo) {
		return dao.getReply(vo);
	}

	public int postReply(ReplyVO vo) {
		ReplyMaxIDVO rmidvo = new ReplyMaxIDVO();
		rmidvo.setReply_id(vo.getReply_id());
		String category = dao.getCategory(vo);
		rmidvo.setCategory(category);
		int replyMaxId = dao.getReplyMaxID(rmidvo);
		vo.setReply_id(category + "_" + (replyMaxId+1));
		vo.setDate(new Date());
		return dao.postReply(vo);
	}

	public List<KidsVO> nextVideoList() {
		// TODO Auto-generated method stub
		return null;
	}
}