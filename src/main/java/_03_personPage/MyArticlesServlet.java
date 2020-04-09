package _03_personPage;

import java.io.IOException;
import java.util.Map;

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
import _06_article.model.ArticleBean;
import _06_article.service.ArticleService;

// 我的文章
@WebServlet("/personPage/showMyArticles")
public class MyArticlesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼

		// 使用逾時，回首頁
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		String arrange = request.getParameter("arrange") == null ? "" : request.getParameter("arrange");
		String searchStr = request.getParameter("search") == null ? "" : request.getParameter("search");

		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ArticleService service = ctx.getBean(ArticleService.class);

		Map<Integer, ArticleBean> articleMap = service.getPersonArticles(arrange, searchStr, mb);
		request.setAttribute("searchStr", searchStr);
		request.setAttribute("arrange", arrange);
		request.setAttribute("articles_map", articleMap);

		// 轉換頁面
		RequestDispatcher rd = request.getRequestDispatcher("/_03_personPage/myArticles.jsp");
		rd.forward(request, response);
		return;

	}
}
