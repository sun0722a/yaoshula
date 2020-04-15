package _06_article.service.impl;

import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _01_register.model.MemberBean;
import _06_article.dao.ArticleDao;
import _06_article.model.ArticleBean;
import _06_article.model.ArticleCategoryBean;
import _06_article.model.CommentBean;
import _06_article.model.ReportArticleBean;
import _06_article.model.ReportCommentBean;
import _06_article.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDao dao;

	public ArticleServiceImpl() {
	}

	@Transactional
	@Override
	public void insertArticle(ArticleBean ab) {
		dao.insertArticle(ab);
	}

	@Transactional
	@Override
	public void insertComment(CommentBean cb) {
		dao.insertComment(cb);
	}

	@Transactional
	@Override
	public void insertReportArticle(ReportArticleBean rab) {
		dao.insertReportArticle(rab);
	}

	@Transactional
	@Override
	public void insertReportComment(ReportCommentBean rcb) {
		dao.insertReportComment(rcb);
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

	@Transactional
	@Override
	public Map<Integer, ArticleBean> getArticles(String arrange, String searchStr, String categoryTitle,
			String categoryName) {
		Map<Integer, ArticleBean> map = null;
		map = dao.getArticles(arrange, searchStr, categoryTitle, categoryName);
		return map;
	}

	@Transactional
	@Override
	public Map<Integer, ArticleBean> getPersonArticles(String arrange, String searchStr, MemberBean mb) {
		Map<Integer, ArticleBean> map = null;
		map = dao.getPersonArticles(arrange, searchStr, mb);
		return map;
	}

	@Transactional
	@Override
	public Map<Integer, ArticleBean> getFamousArticles(String categoryTitle) {
		Map<Integer, ArticleBean> map = null;
		map = dao.getFamousArticles(categoryTitle);
		return map;
	}

	@Transactional
	@Override
	public ArticleCategoryBean getCategory(String categoryTitle, String categoryName) {
		ArticleCategoryBean bean = null;
		bean = dao.getCategory(categoryTitle, categoryName);
		return bean;
	}

	@Transactional
	@Override
	public Set<String> getCategorys(String categoryTitle) {
		Set<String> categorySet = null;
		categorySet = dao.getCategorys(categoryTitle);
		return categorySet;
	}

	@Transactional
	@Override
	public Integer likeArticle(ArticleBean ab, MemberBean mb) {
		int n = 0;
		n = dao.likeArticle(ab, mb);
		return n;
	}

	@Transactional
	@Override
	public Map<ArticleBean, Integer> getReportArticles(String searchStr) {
		Map<ArticleBean, Integer> map = null;
		map = dao.getReportArticles(searchStr);
		return map;
	}

	@Transactional
	@Override
	public Map<CommentBean, Integer> getReportComments(String searchStr) {
		Map<CommentBean, Integer> map = null;
		map = dao.getReportComments(searchStr);
		return map;
	}

	@Transactional
	@Override
	public Integer getReportItemCount(String cmd, Integer id, String item) {
		int n = 0;
		n = dao.getReportItemCount(cmd, id, item);
		return n;
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

	@Transactional
	@Override
	public ArticleBean getArticle(int articleId) {
		ArticleBean bean = null;
		bean = dao.getArticle(articleId);
		return bean;
	}

	@Transactional
	@Override
	public CommentBean getComment(int commentId) {
		CommentBean bean = null;
		bean = dao.getComment(commentId);
		return bean;
	}
}
