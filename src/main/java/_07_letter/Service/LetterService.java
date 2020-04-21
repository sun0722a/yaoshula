package _07_letter.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import _07_letter.model.LetterBean;

public interface LetterService {
	
	public void saveLetter(LetterBean lb);
	
	public LetterBean getLetter(int letterId);
	
	public Map<Integer,LetterBean> getUnfinishedLetter(String category,String status);
	
	public void updateReply(LetterBean lb);
	
//	public Map<String,LetterBean> getAllLettersByMemberSend(String memberId,String status);
	public List<LetterBean> getAllLettersByMemberSend(String memberId,String status);
	
}
