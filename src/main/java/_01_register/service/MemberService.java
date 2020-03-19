package _01_register.service;

import _01_register.model.MemberBean;

public interface MemberService {

	int saveMember(MemberBean mb);

	boolean idExists(String id);

	boolean emailExists(String email);

	MemberBean queryMember(int id);

	MemberBean checkIdPassword(String userId, String password);

	int updateMember(MemberBean mb);

}
