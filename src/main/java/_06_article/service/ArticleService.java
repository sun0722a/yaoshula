package _06_article.service;

import java.util.Map;
import java.util.Set;

import _01_register.model.MemberBean;
import _06_article.model.ArticleBean;
import _06_article.model.ArticleCategoryBean;
import _06_article.model.CommentBean;
import _06_article.model.ReportArticleBean;
import _06_article.model.ReportCommentBean;

public interface ArticleService {

	void insertArticle(ArticleBean ab);

	void insertComment(CommentBean cb);

	void insertReportArticle(ReportArticleBean rab);

	void insertReportComment(ReportCommentBean rcb);

	void deleteReportArticle(Integer articleId);

	void deleteReportComment(Integer commentId);

//	int getTotalPages(String searchStr, String categoryTitle, String categoryName);

//	long getRecordCounts();

	Map<Integer, ArticleBean> getArticles(String arrange, String searchStr, String categoryTitle, String categoryName);

	Map<Integer, ArticleBean> getPersonArticles(String arrange, String searchStr, MemberBean mb);

	Map<Integer, ArticleBean> getFamousArticles(String categoryTitle);

	ArticleCategoryBean getCategory(String categoryTitle, String categoryName);

	Set<String> getCategorys(String categoryTitle);

	Integer likeArticle(ArticleBean ab, MemberBean mb);

	Map<ArticleBean, Integer> getReportArticles(String searchStr);

	Map<CommentBean, Integer> getReportComments(String searchStr);

	Integer getReportItemCount(String cmd, Integer id, String item);

//	List<String> getCategory();

//	String getCategoryTag();

//	int updateBook(BookBean bean, long sizeInBytes);

//	int deleteBook(int no);

//	int saveBook(BookBean bean);

	ArticleBean getArticle(int articleId);

	CommentBean getComment(int commentId);
}
