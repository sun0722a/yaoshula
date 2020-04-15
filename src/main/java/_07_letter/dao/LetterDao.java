package _07_letter.dao;

import java.util.Map;

import _07_letter.model.LetterBean;

public interface LetterDao {
	
	public void saveLetter(LetterBean lb);
	
	public LetterBean getLetter(int letterId);
	
	public Map<Integer,LetterBean> getUnfinishedLetter(String category,String status);
	
	public void updateReply(LetterBean lb);
}
