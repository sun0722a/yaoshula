package _07_letter.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _07_letter.dao.LetterDao;
import _07_letter.model.LetterBean;

@Repository
public class LetterDaoImpl implements LetterDao {
	
	@Autowired
	SessionFactory factory;
	
	public LetterDaoImpl() {
		
	}

	@Override
	public void saveLetter(LetterBean lb) {
		factory.getCurrentSession().save(lb);
		
	}

	@Override
	public LetterBean getLetter(int letterId) {

		return factory.getCurrentSession().get(LetterBean.class, letterId);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer,LetterBean> getUnfinishedLetter(String memberId,String category,String status) {
		Map<Integer,LetterBean> letterMap = new LinkedHashMap<Integer, LetterBean>();
		Session session = factory.getCurrentSession();
		String hql = "FROM LetterBean l WHERE l.letterCategory = :category AND l.status = :status AND l.letterWriter != :memberId";
		List<LetterBean> beans = new ArrayList<LetterBean>();
		beans = session.createQuery(hql).setParameter("category", category).setParameter("status", status).setParameter("memberId", memberId).getResultList();
		for(LetterBean lb : beans) {
			letterMap.put(lb.getLetterId(), lb);
		}
		return letterMap;
	}

	@Override
	public void updateReply(LetterBean lb) {
		
		Session session = factory.getCurrentSession();
		String hql = "UPDATE LetterBean l SET l.replyContent=:reply, l.letterReplier=:replier,l.status =:status WHERE letterId = :letterId";
		session.createQuery(hql)
			   .setParameter("reply", lb.getReplyContent())
			   .setParameter("replier", lb.getLetterReplier())
			   .setParameter("status", lb.getStatus())
			   .setParameter("letterId", lb.getLetterId()).executeUpdate();
		
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public Map<String,LetterBean> getAllLettersByMemberSend(String memberId,String status) {
//		Map<String,LetterBean> letterMap = new LinkedHashMap<String, LetterBean>();
//		Session session = factory.getCurrentSession();
//		String hql = "FROM LetterBean l WHERE l.letterWriter = :memberId AND l.status = :status";
//		List<LetterBean> list = session.createQuery(hql).setParameter("memberId", memberId).setParameter("status", status).getResultList();
//		for(LetterBean lb : list) {
//			letterMap.put(lb.getLetterCategory(), lb);
//		}
//		return letterMap;
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LetterBean> getAllLettersByMemberSend(String memberId,String status) {
		Session session = factory.getCurrentSession();
		String hql = "FROM LetterBean l WHERE l.letterWriter = :memberId AND l.status = :status";
		return session.createQuery(hql).setParameter("memberId", memberId).setParameter("status", status).getResultList();
	}

	@Override
	public void updateLetterOccupied(int letterId, String status) {
		String hql = "UPDATE LetterBean l SET l.status = :status WHERE l.letterId =:letterId";
		factory.getCurrentSession().createQuery(hql).setParameter("status", status).setParameter("letterId", letterId).executeUpdate();
	}
	
}
