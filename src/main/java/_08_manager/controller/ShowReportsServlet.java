package _08_manager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import _06_article.model.ArticleBean;
import _06_article.model.CommentBean;
import _06_article.service.ArticleService;

// 查詢被檢舉文章or留言
@WebServlet("/manager/showReports")
public class ShowReportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 使用逾時，回首頁
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		// 必須是空字串
		String searchStr = request.getParameter("searchStr") == null ? "" : request.getParameter("searchStr");
		String cmd = request.getParameter("cmd") == null ? "article" : request.getParameter("cmd");

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ArticleService articleService = ctx.getBean(ArticleService.class);

		if (cmd.equals("article")) {
			Map<ArticleBean, Integer> articlesTime = articleService.getReportArticles(searchStr);
			Map<ArticleBean, Integer> articlesNum = new LinkedHashMap<>();
			List<Entry<ArticleBean, Integer>> list = new ArrayList<Map.Entry<ArticleBean, Integer>>(
					articlesTime.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<ArticleBean, Integer>>() {
				public int compare(Map.Entry<ArticleBean, Integer> o1, Map.Entry<ArticleBean, Integer> o2) {
					return (o2.getValue() - o1.getValue());
				}
			});
			for (Map.Entry<ArticleBean, Integer> t : list) {
				articlesNum.put(t.getKey(), t.getValue());
			}
			request.setAttribute("article_map", articlesNum);

		} else if (cmd.equals("comment")) {
			Map<CommentBean, Integer> commentsTime = articleService.getReportComments(searchStr);
			Map<CommentBean, Integer> commentsNum = new LinkedHashMap<>();
			List<Entry<CommentBean, Integer>> list = new ArrayList<Map.Entry<CommentBean, Integer>>(
					commentsTime.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<CommentBean, Integer>>() {
				public int compare(Map.Entry<CommentBean, Integer> o1, Map.Entry<CommentBean, Integer> o2) {
					return (o2.getValue() - o1.getValue());
				}
			});
			for (Map.Entry<CommentBean, Integer> t : list) {
				commentsNum.put(t.getKey(), t.getValue());
			}
			request.setAttribute("comment_map", commentsNum);
		}
		request.setAttribute("searchStr", searchStr);
		request.setAttribute("cmd", cmd);

		RequestDispatcher rd = request.getRequestDispatcher("/_08_manager/allReports.jsp");
		rd.forward(request, response);
		return;
	}

}
