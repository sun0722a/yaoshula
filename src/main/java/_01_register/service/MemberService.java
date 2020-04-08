package _01_register.service;

import _01_register.model.MemberBean;

public interface MemberService {

	int saveMember(MemberBean mb);

	boolean idExists(String id);

	boolean emailExists(String email);

//	MemberBean queryMember(String id);

	MemberBean queryMember(int id);

	MemberBean checkIdPassword(String memberId, String password);

	int updateMember(MemberBean mb);

}
