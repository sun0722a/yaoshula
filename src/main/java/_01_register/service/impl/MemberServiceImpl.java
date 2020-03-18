package _01_register.service.impl;

import _01_register.dao.MemberDao;
import _01_register.dao.impl.MemberDaoImpl_Jdbc;
import _01_register.model.MemberBean;
import _01_register.service.MemberService;

public class MemberServiceImpl implements MemberService {

	MemberDao  dao ;
	public MemberServiceImpl() {
		this.dao = new MemberDaoImpl_Jdbc();
	}

	@Override
	public int saveMember(MemberBean mb) {
		return dao.saveMember(mb);
	}

	@Override
	public boolean idExists(int id) {
		return dao.idExists(id);
	}

	@Override
	public MemberBean queryMember(int id) {
		return dao.queryMember(id);
	}

	public MemberBean checkIdPassword(String name, String password) {
		MemberBean mb = dao.checkIdPassword(name, password);
		return mb;
	}
	
	public int updateMember(MemberBean mb) {
		return dao.updateMember(mb);
	}
	
	
}