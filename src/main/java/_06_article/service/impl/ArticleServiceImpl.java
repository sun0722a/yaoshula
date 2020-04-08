package _06_article.service.impl;

import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.HibernateUtils;
import _06_article.dao.ArticleDao;
import _06_article.dao.impl.ArticleDaoImpl_Hibernate;
import _06_article.model.ArticleBean;
import _06_article.model.ArticleCategoryBean;
import _06_article.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {

	ArticleDao dao;
	SessionFactory factory;

	public ArticleServiceImpl() {
		dao = new ArticleDaoImpl_Hibernate();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void insertArticle(ArticleBean ab) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.insertArticle(ab);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

//	@Override
//	public int getTotalPages(String searchStr, String categoryTitle, String categoryName) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		int n = 0;
//		try {
//			tx = session.beginTransaction();
//			n = dao.getTotalPages(searchStr, categoryTitle, categoryName);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
//		return n;
//	}

//	@Override
//	public long getRecordCounts() {
//		return dao.getRecordCounts();
//	}

	@Override
	public Map<Integer, ArticleBean> getArticles(String arrange, String searchStr, String categoryTitle,
			String categoryName) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Map<Integer, ArticleBean> map = null;
		try {
			tx = session.beginTransaction();
			map = dao.getArticles(arrange, searchStr, categoryTitle, categoryName);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return map;
	}

	@Override
	public Map<Integer, ArticleBean> getFamousArticles(String categoryTitle) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Map<Integer, ArticleBean> map = null;
		try {
			tx = session.beginTransaction();
			map = dao.getFamousArticles(categoryTitle);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return map;
	}

	@Override
	public ArticleCategoryBean getCategory(String categoryTitle, String categoryName) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		ArticleCategoryBean bean = null;
		try {
			tx = session.beginTransaction();
			bean = dao.getCategory(categoryTitle, categoryName);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return bean;
	}

	@Override
	public Set<String> getCategorys(String categoryTitle) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		Set<String> categorySet = null;
		try {
			tx = session.beginTransaction();
			categorySet = dao.getCategorys(categoryTitle);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return categorySet;
	}

//	@Override
//	public List<String> getCategory() {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		List<String> list = null;
//		try {
//			tx = session.beginTransaction();
//			list = dao.getCategory();
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
//		return list;
//	}

//	@Override
//	public String getCategoryTag() {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		String tag = "";
//		try {
//			tx = session.beginTransaction();
//			tag = dao.getCategoryTag();
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
//		return tag;
//	}

//	@Override
//	public int updateBook(BookBean bean, long sizeInBytes) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		int n = 0;
//		try {
//			tx = session.beginTransaction();
//			n = dao.updateBook(bean, sizeInBytes);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
//		return n;
//	}

//	@Override
//	public int deleteBook(int no) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		int n = 0;
//		try {
//			tx = session.beginTransaction();
//			n = dao.deleteBook(no);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
//		return n;
//	}

//	@Override
//	public int saveBook(BookBean bean) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		int n = 0;
//		try {
//			tx = session.beginTransaction();
//			n = dao.saveBook(bean);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
//		return n;
//	}

//	@Override
//	public void setSelected(String category) {
//		dao.setSelected(category);
//	}

//	@Override
//	public int getRecordsPerPage() {
//		return dao.getRecordsPerPage();
//	}

//	@Override
//	public void setRecordsPerPage(int recordsPerPage) {
//		dao.setRecordsPerPage(recordsPerPage);
//	}

	// 避免LazyLoading
	@Override
	public ArticleBean getArticle(int articleId) {
		ArticleBean bean = null;
		bean = dao.getArticle(articleId);
		return bean;
	}

}
