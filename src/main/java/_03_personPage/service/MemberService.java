package _03_personPage.service;

import _03_personPage.model.MemberBean;

public interface MemberService {
	boolean idExists(int id);
	int saveMember(MemberBean mb);
	MemberBean queryMember(int id);
	MemberBean checkIdPassword(String userId, String password) ;
	int updateMember(MemberBean mb);
}
