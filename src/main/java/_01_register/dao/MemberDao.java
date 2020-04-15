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
<<<<<<< HEAD
	
	public int updateMemberPassword(String memberId, String  passwordNew);
	
	public void updateSendDate(String memberId,String sendDate);
	
	public void updateReplyDate(String memberId,String replyDate);
	
	public boolean checkSendable(String memberId,String today);
	
	public boolean checkReplyable(String memberId,String today);
=======

	public int updateMemberPassword(String memberId, String passwordNew);

	public MemberBean getMember(int id);
>>>>>>> 701e0f4fb581c9cf3ee62d42a34147887876e6dc
}