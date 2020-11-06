package com.mega.p3.kids;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KidsService implements KidsInterface {
	
	@Autowired
	KidsDAO dao;

	@Override
	public KidsVONamed one(KidsVO kidsVO) {
		return dao.one(kidsVO);
	}

	@Override
	public List<KidsVONamed> listByCategory(KidsVO kidsVO) {
		return dao.listByCategory(kidsVO);
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
