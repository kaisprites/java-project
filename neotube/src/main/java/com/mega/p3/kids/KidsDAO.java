package com.mega.p3.kids;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KidsDAO {
	
	@Autowired
	SqlSessionTemplate mybatis;

	public KidsVOWithChannel one(KidsVO kidsVO) {
		return mybatis.selectOne("one", kidsVO);
	}

	public List<KidsVOWithChannel> listByCategory(KidsVO kidsVO) {
		List<KidsVOWithChannel> result = mybatis.selectList("listByCategory", kidsVO);
		return result;
	}

	public List<KidsVO> listBySearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public void upload(KidsVO vo) {
		// TODO Auto-generated method stub

	}

	public Integer getLike(UserControlVO vo) {
		return mybatis.selectOne("getLike", vo);
	}
	
	public int likeCancel(UserControlVO vo) {
		return mybatis.delete("likeCancel", vo);
	}
	
	public int videoLikeUndo(UserControlVO vo) {
		return mybatis.update("videoLikeUndo", vo);
	}
	
	public int videoDislikeUndo(UserControlVO vo) {
		return mybatis.update("videoDislikeUndo", vo);
	}
	
	public int userLike(UserControlVO vo) {
		return mybatis.insert("userLike", vo);
	}
	
	public int userDislike(UserControlVO vo) {
		return mybatis.insert("userDislike", vo);
	}
	
	public int videoLike(UserControlVO vo) {
		return mybatis.update("videoLike", vo);
	}
	
	public int videoDislike(UserControlVO vo) {
		return mybatis.update("videoDislike", vo);
	}

	public List<ReplyVO> getReply(ReplyVO vo) {
		List<ReplyVO> bag = mybatis.selectList("getReply",vo);
		return bag;
	}
	
}
