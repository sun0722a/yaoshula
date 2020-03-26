package _05_product.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00_init.util.GlobalService;
import _00_init.util.HibernateUtils;
import _05_product.dao.ProductDao;
import _05_product.model.ProductBean;

/* 刪除 : implements Serializable，不知道會不會出事 */

public class ProductDaoImpl_Hibernate implements ProductDao {

	// 預設值：每頁九筆
	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE;
	private int recordsPerFamous = GlobalService.RECORDS_PER_FAMOUS;
	private int totalPages = -1;

	String selected = "";
	SessionFactory factory;

	public ProductDaoImpl_Hibernate() {
		factory = HibernateUtils.getSessionFactory();
	}

	// 計算販售的商品總共有幾頁
	@Override
	public int getTotalPages() {
		// 總共幾頁=無條件進位(共有幾個商品/一頁的商品數)
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}

	// 計算總共有多少商品
	@SuppressWarnings("unchecked")
	@Override
	public long getRecordCounts() {
		String hql = "SELECT count(*) FROM ProductBean";
		Session session = factory.getCurrentSession();

		long count = 0; // 必須使用 long 型態
		List<Long> list = session.createQuery(hql).getResultList();
		if (list.size() > 0) {
			count = list.get(0);
		}
		return count;
	}

	// 查詢某一頁的商品資料
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, ProductBean> getPageProducts(int pageNo) {
		String hql = "FROM ProductBean ";
		Session session = factory.getCurrentSession();

		Map<Integer, ProductBean> map = new HashMap<>();
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		List<ProductBean> list = new ArrayList<ProductBean>();
		list = session.createQuery(hql).setFirstResult(startRecordNo).setMaxResults(recordsPerPage).getResultList();
		for (ProductBean bean : list) {
			map.put(bean.getProductId(), bean);
		}
		return map;
	}

	// 查詢熱門商品(天使or惡魔)
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, ProductBean> getFamousProducts(String categoryTitle) {
		String hql = "";

		if (categoryTitle == "天使") {
			hql = "SELECT pb FROM ProductBean pb,CategoryBean cb WHERE pb.category=cb.categoryId AND cb.categoryTitle= :categoryTitle ORDER BY pb.sales DESC";

		} else if (categoryTitle == "惡魔") {
			hql = "SELECT pb FROM ProductBean pb,CategoryBean cb WHERE pb.category=cb.categoryId AND cb.categoryTitle= :categoryTitle ORDER BY pb.sales DESC";
		} else {
			return null;
		}

		Session session = factory.getCurrentSession();

		Map<Integer, ProductBean> map = new HashMap<>();
		List<ProductBean> list = new ArrayList<ProductBean>();
		list = session.createQuery(hql).setParameter("categoryTitle", categoryTitle).setMaxResults(recordsPerFamous)
				.getResultList();
		for (ProductBean bean : list) {
			map.put(bean.getProductId(), bean);
		}
		return map;
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

	@Override
	public void setSelected(String selected) {
		this.selected = selected;
	}

	@Override
	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	@Override
	public ProductBean getProduct(int productId) {
		Session session = factory.getCurrentSession();
		ProductBean bean = null;
		bean = session.get(ProductBean.class, productId);
		return bean;
	}

}