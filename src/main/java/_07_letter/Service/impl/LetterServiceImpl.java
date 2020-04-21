package _07_letter.Service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _07_letter.Service.LetterService;
import _07_letter.dao.LetterDao;
import _07_letter.model.LetterBean;

@Transactional
@Service
public class LetterServiceImpl implements LetterService{
	
	@Autowired
	LetterDao dao;
	
	
	@Override
	public void saveLetter(LetterBean lb) {		
		dao.saveLetter(lb);
	}
	
	
	
	@Override
	public LetterBean getLetter(int letterId) {
		LetterBean bean = null;
		bean =  dao.getLetter(letterId);
		return bean;
	}

	
	
	@Override
	public Map<Integer,LetterBean> getUnfinishedLetter(String category,String status) {
		Map<Integer,LetterBean> letters = null;
		letters = dao.getUnfinishedLetter(category,status);
		return letters;
	}


	@Override
	public void updateReply(LetterBean lb) {
		dao.updateReply(lb);
		
	}



//	@Override
//	public Map<String,LetterBean> getAllLettersByMemberSend(String memberId,String status) {
//		return dao.getAllLettersByMemberSend(memberId,status);
//	}
	
	@Override
	public List<LetterBean> getAllLettersByMemberSend(String memberId,String status) {
		return dao.getAllLettersByMemberSend(memberId,status);
	}
}
