package _01_register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _01_register.dao.MemberDao;
import _01_register.model.MemberBean;
import _01_register.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;

	public MemberServiceImpl() {
	}

	@Transactional
	@Override
	public int saveMember(MemberBean mb) {
		int count = 0;
		dao.saveMember(mb);
		count++;
		return count;
	}

	@Transactional
	@Override
	public boolean idExists(String id) {
		boolean result;
		result = dao.idExists(id);
		return result;
	}

	@Transactional
	@Override
	public boolean emailExists(String email) {
		boolean result;
		result = dao.emailExists(email);
		return result;

	}

	@Transactional
	@Override
	public MemberBean getMember(int id) {
		MemberBean mb = null;
		mb = dao.getMember(id);
		return mb;
	}

	@Transactional
	@Override
	public MemberBean checkIdPassword(String memberId, String password) {
		MemberBean mb = null;
		mb = dao.checkIdPassword(memberId, password);
		return mb;
	}

	@Transactional
	@Override
	public int updateMember(MemberBean mb) {
		int count = 0;
		dao.updateMember(mb);
		count++;
		return count;
	}

}
