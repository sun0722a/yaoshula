package _01_register.dao;

import java.sql.Connection;

import _01_register.model.MemberBean;

public interface MemberDao {

	public int saveMember(MemberBean mb);

	public boolean idExists(String id);

	public boolean emailExists(String email);

	public MemberBean queryMember(int id);

	public MemberBean checkIdPassword(String userId, String password);

	public int updateMember(MemberBean mb);

	public void setConnection(Connection con);

}