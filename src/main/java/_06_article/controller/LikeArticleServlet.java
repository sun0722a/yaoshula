package _06_article.controller;

import java.io.IOException;

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
import _06_article.service.ArticleService;

// 更新文章按讚數
@WebServlet("/article/LikeArticle")
public class LikeArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 使用逾時，回首頁
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		ArticleBean ab = (ArticleBean) session.getAttribute("article");
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		String login = (String) request.getParameter("login");

		if (login == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/article/ShowArticleContent?articleId="
					+ ab.getArticleId());
			return;
		}

		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		MemberService memberService = ctx.getBean(MemberService.class);
		ArticleService articleService = ctx.getBean(ArticleService.class);
		articleService.likeArticle(ab, mb);

		// 更新session內的使用者資料
		MemberBean newMb = memberService.getMember(mb.getId());
		session.setAttribute("LoginOK", newMb);

		response.sendRedirect(
				getServletContext().getContextPath() + "/article/ShowArticleContent?articleId=" + ab.getArticleId());
		return;
	}
}
