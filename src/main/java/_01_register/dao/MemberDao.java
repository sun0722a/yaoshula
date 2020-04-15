package _01_register.dao;

import java.util.Map;

import _01_register.model.MemberBean;

public interface MemberDao {

	public int saveMember(MemberBean mb);

	public Map<MemberBean, Integer> getMembers(String searchStr);

	public boolean idExists(String id);

	public boolean emailExists(String email);

	public int updateMember(MemberBean mb);

	public MemberBean checkIdPassword(String memberId, String password);

	public MemberBean getEmailValid(String emailCode);

	public int updateMemberPassword(String memberId, String passwordNew);

	public MemberBean getMember(int id);
}