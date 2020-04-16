package _08_manager.report;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _00_init.util.GlobalService;
import _06_article.model.ArticleBean;
import _06_article.model.CommentBean;
import _06_article.service.ArticleService;

// 查詢被檢舉文章or留言詳細資料
@WebServlet("/manager/showReportInfo")
public class ReportInfoServlet extends HttpServlet {
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

		String cmd = request.getParameter("cmd");
		String idStr = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			;
		}
		String[] reportItems = GlobalService.REPORT_ITEM;

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ArticleService articleService = ctx.getBean(ArticleService.class);

		// ("item0", 5)
		for (Integer i = 0; i < reportItems.length; i++) {
			int count = articleService.getReportItemCount(cmd, id, reportItems[i]);
			request.setAttribute("item" + i, count);
		}
		if (cmd.equals("article")) {
			ArticleBean ab = articleService.getArticle(id);
			String content = "";
			Clob clob = null;
			if (ab != null) {
				try {
					clob = ab.getContent();
					if (clob != null) {
						content = GlobalService.clobToString(clob);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			request.setAttribute("article", ab);
			request.setAttribute("content", content);
		} else if (cmd.equals("comment")) {
			CommentBean cb = articleService.getComment(id);
			request.setAttribute("comment", cb);
		}
		request.setAttribute("cmd", cmd);
		request.setAttribute("id", idStr);

		RequestDispatcher rd = request.getRequestDispatcher("/_08_manager/report/reportInfo.jsp");
		rd.forward(request, response);
		return;
	}

}
