package _07_letter.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public LetterBean getLetter(LetterBean lb) {
		
		return lb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer,LetterBean> getUnfinishedLetter(String category,String status) {
		Map<Integer,LetterBean> letterMap = new LinkedHashMap<Integer, LetterBean>();
		Session session = factory.getCurrentSession();
		String hql = "FROM LetterBean l Where l.letterCategory = :category AND l.status = :status";
		List<LetterBean> beans = new ArrayList<LetterBean>();
		beans = session.createQuery(hql).setParameter("letterCategory", category).setParameter("status", status).getResultList();
		for(LetterBean lb : beans) {
			letterMap.put(lb.getLetterNo(), lb);
		}
		return letterMap;
	}
	
}
