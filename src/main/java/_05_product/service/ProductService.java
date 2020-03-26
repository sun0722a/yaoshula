package _05_product.service;

import java.util.Map;

import _05_product.model.ProductBean;

public interface ProductService {

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

}
