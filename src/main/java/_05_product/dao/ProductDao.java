package _05_product.dao;

import java.util.List;
import java.util.Map;

import _05_product.model.ProductBean;

public interface ProductDao {

	int getTotalPages();

	long getRecordCounts();

	Map<Integer, ProductBean> getPageProducts(int pageNo);

//	List<String> getCategory();
//
//	String getCategoryTag();
//
//	int updateBook(BookBean bean, long sizeInBytes);
//
//	int deleteBook(int no);
//
//	int saveBook(BookBean bean);

	void setSelected(String selected);

	int getRecordsPerPage();

	void setRecordsPerPage(int recordsPerPage);

	ProductBean getProduct(int productId);
	
	List<ProductBean> getProductInfo(int productId);

}