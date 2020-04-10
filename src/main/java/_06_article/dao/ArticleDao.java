package _06_article.dao;

import java.util.Map;
import java.util.Set;

import _01_register.model.MemberBean;
import _06_article.model.ArticleBean;
import _06_article.model.ArticleCategoryBean;
import _06_article.model.CommentBean;
import _06_article.model.ReportArticleBean;
import _06_article.model.ReportCommentBean;

public interface ArticleDao {

	void insertArticle(ArticleBean ab);

	void insertComment(CommentBean cb);

	void insertReportArticle(ReportArticleBean rab);

	void insertReportComment(ReportCommentBean rcb);

//	int getTotalPages(String searchStr, String categoryTitle, String categoryName);

//	long getRecordCounts();

	Map<Integer, ArticleBean> getArticles(String arrange, String searchStr, String categoryTitle, String categoryName);

	Map<Integer, ArticleBean> getPersonArticles(String arrange, String searchStr, MemberBean mb);

	Map<Integer, ArticleBean> getFamousArticles(String categoryTitle);

	ArticleCategoryBean getCategory(String categoryTitle, String categoryName);

	Set<String> getCategorys(String categoryTitle);

	Integer likeArticle(ArticleBean ab, MemberBean mb);

//	List<String> getCategory();

//	String getCategoryTag();

//	int updateBook(BookBean bean, long sizeInBytes);

//	int deleteBook(int no);

//	int saveBook(BookBean bean);

//	void setSelected(String selected);

//	int getRecordsPerPage();

//	void setRecordsPerPage(int recordsPerPage);

	ArticleBean getArticle(int articleId);

	CommentBean getComment(int commentId);

}