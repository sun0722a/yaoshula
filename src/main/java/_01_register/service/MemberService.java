package _01_register.service;

import _01_register.model.MemberBean;

public interface MemberService {
	boolean idExists(int id);
	int saveMember(MemberBean mb);
	MemberBean queryMember(int id);
	MemberBean checkIdPassword(String userId, String password) ;
	int updateMember(MemberBean mb);
	
	
}
