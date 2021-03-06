package _01_register.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _01_register.dao.MemberDao;
import _01_register.model.MemberBean;
import _01_register.service.MemberService;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;

	public MemberServiceImpl() {
	}

	
	@Override
	public int saveMember(MemberBean mb) {
		int count = 0;
		dao.saveMember(mb);
		count++;
		return count;
	}

	
	@Override
	public Map<MemberBean, Integer> getMembers(String searchStr) {
		Map<MemberBean, Integer> map = null;
		map = dao.getMembers(searchStr);
		return map;
	}

	@Transactional
	@Override
	public boolean idExists(String id) {
		boolean result;
		result = dao.idExists(id);
		return result;
	}

	
	@Override
	public boolean emailExists(String email) {
		boolean result;
		result = dao.emailExists(email);
		return result;

	}

	
	@Override
	public int updateMember(MemberBean mb) {
		int count = 0;
		dao.updateMember(mb);
		count++;
		return count;
	}

	
	@Override
	public MemberBean checkIdPassword(String memberId, String password) {
		MemberBean mb = null;
		mb = dao.checkIdPassword(memberId, password);
		return mb;
	}

	
	@Override
	public MemberBean getEmailValid(String emailCode) {
		MemberBean mb = null;
		mb = dao.getEmailValid(emailCode);
		return mb;
	}

	
	@Override
	public int updateMemberPassword(String memberId, String passwordNew) {
		int count = 0;
		dao.updateMemberPassword(memberId, passwordNew);
		count++;
		return count;
	}

	@Override
	public void updateSendDate(String memberId, String sendDate) {
		dao.updateSendDate(memberId, sendDate);
		
	}
	
	@Override
	public void updateReplyDate(String memberId, String replyDate) {
		dao.updateReplyDate(memberId, replyDate);
	}


	@Transactional
	@Override
	public MemberBean getMember(int id) {
		MemberBean mb = null;
		mb = dao.getMember(id);
		return mb;
	}


	@Override
	public void updateLetterOftheDay(String memberId, int letterId) {
		dao.updateLetterOftheDay(memberId, letterId);
	}


}
