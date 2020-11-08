package com.mega.p3.kids;

import java.util.List;

public interface KidsInterface {
	public KidsVOWithChannel one(KidsVO kidsVO);
	public List<KidsVOWithChannel> listByCategory(KidsVO kidsVO);
	public List<KidsVO> listBySearch(String query);
	public void upload(KidsVO kidsVO);
}
