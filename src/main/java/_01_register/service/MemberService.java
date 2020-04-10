package _01_register.service;

import _01_register.model.MemberBean;

public interface MemberService {

	public int saveMember(MemberBean mb);

	public boolean idExists(String id);

	public boolean emailExists(String email);

	public MemberBean getMember(int id);

	public MemberBean checkIdPassword(String memberId, String password);

	public int updateMember(MemberBean mb);

}
