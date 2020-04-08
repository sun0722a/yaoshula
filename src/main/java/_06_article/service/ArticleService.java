package _06_article.service;

import java.util.Map;
import java.util.Set;

import _06_article.model.ArticleBean;
import _06_article.model.ArticleCategoryBean;

public interface ArticleService {

	void insertArticle(ArticleBean ab);
	
//	int getTotalPages(String searchStr, String categoryTitle, String categoryName);

//	long getRecordCounts();

	Map<Integer, ArticleBean> getArticles(String arrange, String searchStr, String categoryTitle, String categoryName);

	Map<Integer, ArticleBean> getFamousArticles(String categoryTitle);
	
	ArticleCategoryBean getCategory(String categoryTitle, String categoryName);

	Set<String> getCategorys(String categoryTitle);
	
//	List<String> getCategory();

//	String getCategoryTag();

//	int updateBook(BookBean bean, long sizeInBytes);

//	int deleteBook(int no);

//	int saveBook(BookBean bean);

//	void setSelected(String selected);

//	int getRecordsPerPage();

//	void setRecordsPerPage(int recordsPerPage);

	ArticleBean getArticle(int articleId);
	
	ArticleBean getArticleByTransaction(int articleId);

}
