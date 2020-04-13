package _07_letter.Service.impl;

import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _07_letter.Service.LetterService;
import _07_letter.dao.impl.LetterDao;
import _07_letter.model.LetterBean;

@Service
public class LetterServiceImpl implements LetterService{
	
	@Autowired
	LetterDao dao;
	
	@Transactional
	@Override
	public void saveLetter(LetterBean lb) {		
		dao.saveLetter(lb);
	}
	
	
	@Transactional
	@Override
	public LetterBean getLetter(LetterBean lb) {
		LetterBean bean = null;
		bean =  dao.getLetter(lb);
		return bean;
	}

	
	@Transactional
	@Override
	public Map<Integer,LetterBean> getUnfinishedLetter(String category,String status) {
		Map<Integer,LetterBean> letters = null;
		letters = dao.getUnfinishedLetter(category,status);
		return letters;
	}

}
