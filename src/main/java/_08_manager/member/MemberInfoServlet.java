package _08_manager.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _01_register.model.MemberBean;
import _01_register.service.MemberService;
import _06_article.model.ArticleBean;
import _06_article.model.CommentBean;
import _06_article.service.ArticleService;

// 查詢會員資料
@WebServlet("/manager/showMemberInfo")
public class MemberInfoServlet extends HttpServlet {
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

		String cmd = request.getParameter("cmd") == null ? "article" : request.getParameter("cmd");
		String idStr = request.getParameter("id");
		String reportTimes = request.getParameter("reportTimes");
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			;
		}

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberService memberService = ctx.getBean(MemberService.class);
		ArticleService articleService = ctx.getBean(ArticleService.class);
		MemberBean mb = memberService.getMember(id);

		
		
		
		if (cmd.equals("article")) {
			Map<ArticleBean, Integer> articles = articleService.getPersonArticle(id);
			// 照value值排序
			Map<ArticleBean, Integer> articlesNum = new LinkedHashMap<>();
			List<Entry<ArticleBean, Integer>> list = new ArrayList<Map.Entry<ArticleBean, Integer>>(
					articles.entrySet());
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
			Map<CommentBean, Integer> comments = articleService.getPersonComment(id);
			// 照value值排序
			Map<CommentBean, Integer> commentsNum = new LinkedHashMap<>();
			List<Entry<CommentBean, Integer>> list = new ArrayList<Map.Entry<CommentBean, Integer>>(
					comments.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<CommentBean, Integer>>() {
				public int compare(Map.Entry<CommentBean, Integer> o1, Map.Entry<CommentBean, Integer> o2) {
					return (o2.getValue() - o1.getValue());
				}
			});
			for (Map.Entry<CommentBean, Integer> t : list) {
				commentsNum.put(t.getKey(), t.getValue());
			}
			request.setAttribute("comment_map", commentsNum);

		} else if (cmd.equals("deleteArticle")) {
			Map<ArticleBean, Integer> articles = articleService.getPersonDeleteArticle(id);
			// 照value值排序
			Map<ArticleBean, Integer> articlesNum = new LinkedHashMap<>();
			List<Entry<ArticleBean, Integer>> list = new ArrayList<Map.Entry<ArticleBean, Integer>>(
					articles.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<ArticleBean, Integer>>() {
				public int compare(Map.Entry<ArticleBean, Integer> o1, Map.Entry<ArticleBean, Integer> o2) {
					return (o2.getValue() - o1.getValue());
				}
			});
			for (Map.Entry<ArticleBean, Integer> t : list) {
				articlesNum.put(t.getKey(), t.getValue());
			}
			request.setAttribute("article_map", articlesNum);

		} else if (cmd.equals("deleteComment")) {
			Map<CommentBean, Integer> comments = articleService.getPersonDeleteComment(id);
			// 照value值排序
			Map<CommentBean, Integer> commentsNum = new LinkedHashMap<>();
			List<Entry<CommentBean, Integer>> list = new ArrayList<Map.Entry<CommentBean, Integer>>(
					comments.entrySet());
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
		request.setAttribute("id", idStr);
		request.setAttribute("mb", mb);
		request.setAttribute("reportTimes", reportTimes);

		RequestDispatcher rd = request.getRequestDispatcher("/_08_manager/member/memberInfo.jsp");
		rd.forward(request, response);
		return;
	}

}
