package _06_article.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _00_init.util.GlobalService;
import _00_init.util.HibernateUtils;
import _06_article.dao.ArticleDao;
import _06_article.model.ArticleBean;
import _06_article.model.ArticleCategoryBean;
import _06_article.model.CommentBean;

/* 查詢文章: 預設按讚數排列? */
/* 查詢熱門文章: 是否要有 天使-時事 、熱門文章個數*/

@Repository
public class ArticleDaoImpl_Hibernate implements ArticleDao {

	private int recordsPerFamous = GlobalService.RECORDS_PER_FAMOUS;
	@Autowired
	SessionFactory factory;

	public ArticleDaoImpl_Hibernate() {
//		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void insertArticle(ArticleBean ab) {
		Session session = factory.getCurrentSession();
		session.save(ab);
	}

	@Override
	public void insertComment(CommentBean cb) {
		Session session = factory.getCurrentSession();
		session.save(cb);
	}

	// 計算所有商品總共有幾頁
//	@Override
//	public int getTotalPages(String searchStr, String categoryTitle, String categoryName) {
//		if (searchStr == "" && categoryTitle == "" && categoryName == "") {
//			// 總共幾頁=無條件進位(共有幾個商品/一頁的商品數)
//			int totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
//			return totalPages;
//		} else {
//			return nowTotalPages;
//		}
//	}

	// 計算總共有多少商品
//	@SuppressWarnings("unchecked")
//	@Override
//	public long getRecordCounts() {
//		String hql = "SELECT count(*) FROM ProductBean";
//		Session session = factory.getCurrentSession();
//
//		long count = 0; // 必須使用 long 型態
//		List<Long> list = session.createQuery(hql).getResultList();
//		if (list.size() > 0) {
//			count = list.get(0);
//		}
//		return count;
//	}

	// 查詢文章
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, ArticleBean> getArticles(String arrange, String searchStr, String categoryTitle,
			String categoryName) {
		// 預設的搜尋
		String hql = "SELECT ab FROM ArticleBean ab, ArticleCategoryBean acb WHERE ab.category=acb.categoryId "
				+ "AND ab.title LIKE :searchStr " + "AND acb.categoryTitle LIKE :categoryTitle "
				+ "AND acb.categoryName LIKE :categoryName " + "ORDER BY ab.likes DESC";
		Session session = factory.getCurrentSession();
		String[] arranges = GlobalService.ARTICLE_ARRANGE; // "popular", "time"
		Map<Integer, ArticleBean> map = new LinkedHashMap<Integer, ArticleBean>();
		List<ArticleBean> list = new ArrayList<ArticleBean>();

		// 判斷要如何排列
		if (arrange != "") {
			if (arrange.equals(arranges[1])) {
				hql = "SELECT ab FROM ArticleBean ab, ArticleCategoryBean acb WHERE ab.category=acb.categoryId "
						+ "AND ab.title LIKE :searchStr " + "AND acb.categoryTitle LIKE :categoryTitle "
						+ "AND acb.categoryName LIKE :categoryName " + "ORDER BY ab.publishTime DESC";
			}
		}
		// 只取此頁的商品
		list = session.createQuery(hql).setParameter("searchStr", "%" + searchStr + "%")
				.setParameter("categoryTitle", "%" + categoryTitle + "%")
				.setParameter("categoryName", "%" + categoryName + "%").getResultList();
		for (ArticleBean bean : list) {
			map.put(bean.getArticleId(), bean);
		}
		return map;
	}

	// 查詢熱門文章(天使or惡魔)
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, ArticleBean> getFamousArticles(String categoryTitle) {
		String hql = "SELECT ab FROM ArticleBean ab,ArticleCategoryBean acb WHERE ab.category=acb.categoryId AND acb.categoryTitle= :categoryTitle ORDER BY ab.likes DESC";
		Session session = factory.getCurrentSession();

		Map<Integer, ArticleBean> map = new LinkedHashMap<Integer, ArticleBean>();
		List<ArticleBean> list = new ArrayList<ArticleBean>();
		list = session.createQuery(hql).setParameter("categoryTitle", categoryTitle).setMaxResults(recordsPerFamous)
				.getResultList();
		for (ArticleBean bean : list) {
			map.put(bean.getArticleId(), bean);
		}
		return map;
	}

	@Override
	public ArticleCategoryBean getCategory(String categoryTitle, String categoryName) {
		String hql = "FROM ArticleCategoryBean acb " + "WHERE acb.categoryTitle like :categoryTitle "
				+ "AND acb.categoryName like :categoryName";
		Session session = factory.getCurrentSession();
		ArticleCategoryBean bean = null;
		bean = (ArticleCategoryBean) session.createQuery(hql).setParameter("categoryTitle", categoryTitle)
				.setParameter("categoryName", categoryName).getSingleResult();
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getCategorys(String categoryTitle) {
		String hql = "FROM ArticleCategoryBean acb WHERE acb.categoryTitle like :categoryTitle ";
		Session session = factory.getCurrentSession();
		List<ArticleCategoryBean> beans = null;
		Set<String> categorySet = null;
		beans = (List<ArticleCategoryBean>) session.createQuery(hql).setParameter("categoryTitle", categoryTitle)
				.getResultList();
		for (ArticleCategoryBean bean : beans) {
			categorySet.add(bean.getCategoryName());
		}
		return categorySet;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<String> getCategory() {
//		String hql = "SELECT DISTINCT category FROM ProductBean";
//		Session session = factory.getCurrentSession();
//		List<String> list = null;
//		list = session.createQuery(hql).getResultList();
//		return list;
//	}

//	@Override
//	public String getCategoryTag() {
//		String ans = "";
//		List<String> list = getCategory();
//		ans += "<SELECT name='category'>";
//		for (String cate : list) {
//			if (cate.equals(selected)) {
//				ans += "<option value='" + cate + "' selected>" + cate + "</option>";
//			} else {
//				ans += "<option value='" + cate + "'>" + cate + "</option>";
//			}
//		}
//		ans += "</SELECT>";
//		return ans;
//	}

	// 修改一筆書籍資料
//	@Override
//	public int updateBook(BookBean bean, long sizeInBytes) {
//		int n = 0;
//		if (bean.getCompanyBean() == null) {
//			CompanyDao dao = new CompanyDaoImpl_Hibernate();
//			dao.setId(bean.getCompanyId());
//			CompanyBean cb = dao.getCompanyById();
//			bean.setCompanyBean(cb);
//		}
//		if (sizeInBytes == -1) { // 不修改圖片
//			n = updateBook(bean);
//			return n;
//		}
//		Session session = factory.getCurrentSession();
//		session.saveOrUpdate(bean);
//		n++;
//		return n;
//	}

	// 修改一筆書籍資料，不改圖片
//	public int updateBook(BookBean bean) {
//		int n = 0;
//		BookBean b0 = null;
//		Session session = factory.getCurrentSession();
//		b0 = session.get(BookBean.class, bean.getBookId());
//		bean.setStock(b0.getStock());
//		bean.setPriceStr(b0.getPriceStr());
//		bean.setCoverImage(b0.getCoverImage());
//		bean.setFileName(b0.getFileName());
//		session.evict(b0);
//		session.saveOrUpdate(bean);
//		n++;
//		return n;
//	}

	// 依bookId來刪除單筆記錄
//	@Override
//	public int deleteBook(int no) {
//		int n = 0;
//		Session session = factory.getCurrentSession();
//		BookBean bb = new BookBean();
//		bb.setBookId(no);
//		session.delete(bb);
//		n++;
//		return n;
//	}

	// 新增一筆記錄---
//	@Override
//	public int saveBook(BookBean bean) {
//		int n = 0;
//		Session session = factory.getCurrentSession();
//		if (bean.getCompanyBean() == null) {
//			CompanyDao dao = new CompanyDaoImpl_Hibernate();
//			dao.setId(bean.getCompanyId());
//			CompanyBean cb = dao.getCompanyById();
//			bean.setCompanyBean(cb);
//		}
//		session.save(bean);
//		n++;
//		return n;
//	}

//	@Override
//	public void setSelected(String selected) {
//		this.selected = selected;
//	}

//	@Override
//	public int getRecordsPerPage() {
//		return recordsPerPage;
//	}

//	@Override
//	public void setRecordsPerPage(int recordsPerPage) {
//		this.recordsPerPage = recordsPerPage;
//	}

	@Override
	public ArticleBean getArticle(int articleId) {
		Session session = factory.getCurrentSession();
		ArticleBean bean = null;
		bean = session.get(ArticleBean.class, articleId);
		return bean;
	}

}