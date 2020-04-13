package _07_letter.dao.impl;

import java.util.Map;
import java.util.Set;

import _07_letter.model.LetterBean;

public interface LetterDao {
	
	public void saveLetter(LetterBean lb);
	
	public LetterBean getLetter(LetterBean lb);
	
	public Map<Integer,LetterBean> getUnfinishedLetter(String category,String status);
}
