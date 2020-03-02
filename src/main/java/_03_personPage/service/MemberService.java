package _03_personPage.service;

import _03_personPage.model.MemberBean;

public interface MemberService {
	boolean idExists(String id);
	int saveMember(MemberBean mb);
	MemberBean queryMember(String id);
	MemberBean checkIdPassword(String userId, String password) ;
}
