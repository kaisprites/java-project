package com.mega.p3.kids;

import java.util.List;

public interface KidsInterface {
	public KidsVONamed one(KidsVO kidsVO);
	public List<KidsVONamed> listByCategory(KidsVO kidsVO);
	public List<KidsVO> listBySearch(String query);
	public void upload(KidsVO kidsVO);
}
