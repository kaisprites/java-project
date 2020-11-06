package com.mega.p3.kids;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KidsDAO implements KidsInterface {
	
	@Autowired
	SqlSessionTemplate mybatis;

	@Override
	public KidsVONamed one(KidsVO kidsVO) {
		return mybatis.selectOne("one", kidsVO);
	}

	@Override
	public List<KidsVONamed> listByCategory(KidsVO kidsVO) {
		List<KidsVONamed> result = mybatis.selectList("listByCategory", kidsVO);
		return result;
	}

	@Override
	public List<KidsVO> listBySearch(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upload(KidsVO vo) {
		// TODO Auto-generated method stub

	}

}
