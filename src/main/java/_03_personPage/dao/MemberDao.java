package _03_personPage.dao;

import java.sql.Connection;

import _03_personPage.model.MemberBean;


public interface MemberDao {
	
	public boolean idExists(int id);

	public int saveMember(MemberBean mb) ;
	
	public int updateMember(MemberBean mb);
	
	public MemberBean queryMember(int id);
	
	public MemberBean checkIdPassword(String userId, String password);	
	
	public void setConnection(Connection con);
}