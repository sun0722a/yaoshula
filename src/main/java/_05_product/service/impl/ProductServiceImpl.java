package _05_product.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _05_product.dao.ProductDao;

import _05_product.model.ProductBean;
import _05_product.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao dao;
//	SessionFactory factory;

	public ProductServiceImpl() {
//		dao = new ProductDaoImpl_Hibernate();
//		factory = HibernateUtils.getSessionFactory();
	}
	
	@Transactional
	@Override
	public int getTotalPages(String searchStr, String categoryTitle, String categoryName) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
		int n = 0;
//		try {
//			tx = session.beginTransaction();
			n = dao.getTotalPages(searchStr, categoryTitle, categoryName);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
		return n;
	}
	
	
	@Override
	public long getRecordCounts() {
		return dao.getRecordCounts();
	}
	
	@Transactional
	@Override
	public Map<Integer, ProductBean> getPageProducts(int pageNo, String arrange, String searchStr, String categoryTitle,
			String categoryName) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
		Map<Integer, ProductBean> map = null;
//		try {
//			tx = session.beginTransaction();
			map = dao.getPageProducts(pageNo, arrange, searchStr, categoryTitle, categoryName);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
		return map;
	}
	
	@Transactional
	@Override
	public Map<Integer, ProductBean> getFamousProducts(String categoryTitle) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
		Map<Integer, ProductBean> map = null;
//		try {
//			tx = session.beginTransaction();
			map = dao.getFamousProducts(categoryTitle);
//			tx.commit();
//		} catch (Exception ex) {
//			if (tx != null)
//				tx.rollback();
//			ex.printStackTrace();
//			throw new RuntimeException(ex);
//		}
		return map;
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

	@Override
	public void setSelected(String category) {
		dao.setSelected(category);
	}

	@Override
	public int getRecordsPerPage() {
		return dao.getRecordsPerPage();
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		dao.setRecordsPerPage(recordsPerPage);
	}

	// 避免LazyLoading
	@Transactional
	@Override
	public ProductBean getProduct(int productId) {
		ProductBean bean = null;
		bean = dao.getProduct(productId);
		return bean;
	}

}
