package _01_register.service;

import _01_register.model.MemberBean;

public interface MemberService {

	public int saveMember(MemberBean mb);

	public boolean idExists(String id);

	public boolean emailExists(String email);

	public int updateMember(MemberBean mb);

	public MemberBean checkIdPassword(String memberId, String password);

	public MemberBean getMember(int id);
	
	public MemberBean getEmailValid(String emailCode);
	
	public int updateMemberPassword(String memberId, String  passwordNew);
}
