package _07_letter.dao;

import java.util.List;
import java.util.Map;

import _07_letter.model.LetterBean;

public interface LetterDao {
	
	public void saveLetter(LetterBean lb);
	
	public LetterBean getLetter(int letterId);
	
	public Map<Integer,LetterBean> getUnfinishedLetter(String memberId,String category,String status);
	
	public void updateReply(LetterBean lb);
	
	public void updateLetterOccupied(int letterId,String status);
	
//	public Map<String,LetterBean> getAllLettersByMemberSend(String memberId,String status);
	public List<LetterBean> getAllLettersByMemberSend(String memberId,String status);
}
