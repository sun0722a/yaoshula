package _07_letter.Service;

import java.util.Map;

import _07_letter.model.LetterBean;

public interface LetterService {
	
	public void saveLetter(LetterBean lb);
	
	public LetterBean getLetter(LetterBean lb);

	public Map<Integer,LetterBean> getUnfinishedLetter(String category,String status);
}
