package _05_product.dao;

import java.util.Map;

import _04_order.model.OrderBean;
import _05_product.model.ProductBean;

public interface ProductDao {

	int getTotalPages(String searchStr, String categoryTitle, String categoryName);

	long getRecordCounts();

	Map<Integer, ProductBean> getPageProducts(int pageNo, String arrange, String searchStr, String categoryTitle,
			String categoryName);

	Map<Integer, ProductBean> getFamousProducts(String categoryTitle);

//	List<String> getCategory();
//
//	String getCategoryTag();
//
//	int updateBook(BookBean bean, long sizeInBytes);
//
//	int deleteBook(int no);
//
//	int saveBook(BookBean bean);

	int addSales(OrderBean pb);

	ProductBean getProduct(int productId);

}