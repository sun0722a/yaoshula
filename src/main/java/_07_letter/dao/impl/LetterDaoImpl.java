package _07_letter.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
		
		Session session = factory.getCurrentSession();
		session.save(lb);
		
	}

	@Override
	public LetterBean getLetter(int letterId) {
		LetterBean lb = null;
//		String hql = "FROM LetterBean l WHERE l.letterId = :letterId";
		Session session = factory.getCurrentSession(); 
//		lb = session.createQuery(hql).setParameter("letterId", letterId).getSingleResult();
		lb = session.get(LetterBean.class, letterId);
		return lb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer,LetterBean> getUnfinishedLetter(String category,String status) {
		Map<Integer,LetterBean> letterMap = new LinkedHashMap<Integer, LetterBean>();
		Session session = factory.getCurrentSession();
		String hql = "FROM LetterBean l WHERE l.letterCategory = :category AND l.status = :status";
		List<LetterBean> beans = new ArrayList<LetterBean>();
		beans = session.createQuery(hql).setParameter("category", category).setParameter("status", status).getResultList();
		for(LetterBean lb : beans) {
			letterMap.put(lb.getLetterId(), lb);
		}
		return letterMap;
	}

	@Override
	public void updateReply(LetterBean lb) {
		
		Session session = factory.getCurrentSession();
		String hql = "UPDATE LetterBean l SET l.ReplyContent=:reply, l.letterReplier=:replier,l.status =:status WHERE letterId = :letterId";
		session.createQuery(hql)
			   .setParameter("reply", lb.getReplyContent())
			   .setParameter("replier", lb.getLetterReplier())
			   .setParameter("status", lb.getStatus())
			   .setParameter("letterId", lb.getLetterId()).executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, LetterBean> getAllLettersByMember(String memberId,String status) {
		Session session = factory.getCurrentSession();
		Map<Integer, LetterBean> beanMap = new LinkedHashMap<Integer, LetterBean>();
		List<LetterBean> list = null;
		String hql = "FROM LetterBean l WHERE l.letterWriter = :memberId AND l.status = :status";
		list = session.createQuery(hql).setParameter("memberId", memberId).setParameter("status", status).getResultList();
		for(LetterBean lb : list) {
			beanMap.put(lb.getLetterId(), lb);
		}
		return beanMap;
	}
	
}
