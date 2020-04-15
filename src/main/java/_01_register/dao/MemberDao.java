package _01_register.dao;

import _01_register.model.MemberBean;

public interface MemberDao {

	public int saveMember(MemberBean mb);

	public boolean idExists(String id);

	public boolean emailExists(String email);

	public int updateMember(MemberBean mb);

	public MemberBean checkIdPassword(String memberId, String password);

	public MemberBean getMember(int id);
	
	public MemberBean getEmailValid(String emailCode);
	
	public int updateMemberPassword(String memberId, String  passwordNew);
	
	public void updateSendDate(String memberId,String sendDate);
	
	public void updateReplyDate(String memberId,String replyDate);
	
	public boolean checkSendable(String memberId,String today);
	
	public boolean checkReplyable(String memberId,String today);
}