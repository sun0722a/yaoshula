package _06_article.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import _00_init.util.GlobalService;
import _01_register.model.MemberBean;
import _06_article.model.ArticleBean;
import _06_article.model.ArticleCategoryBean;
import _06_article.service.ArticleService;
import _06_article.service.impl.ArticleServiceImpl;

/* hibernate save的回傳值? */
/* category如何轉成categoryTitle、categoryName */

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/article/AddArticle")
public class AddArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		// 準備存放錯誤訊息的Map物件
//		Map<String, String> errorMsg = new HashMap<String, String>();
//		request.setAttribute("errorMsg", errorMsg); // 顯示錯誤訊息

		String categoryTitle = "";
		String categoryName = "";
		String title = "";
		String content = "";
		String fileName = "";
		long sizeInBytes = 0;
		InputStream is = null;

		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		Integer authorId = mb.getId();
		String authorName = mb.getMemberId();

		// 取出HTTP multipart request內所有的parts
		Collection<Part> parts = request.getParts();
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
//				System.out.println("fldName= " + fldName + "value= " + value);

				// 逐項讀取使用者輸入資料
				if (p.getContentType() == null) {
					if (fldName.equals("categoryTitle")) {
						categoryTitle = value;
					}else if (fldName.equals("categoryName")) {
						categoryName = value;
					}else if (fldName.equals("title")) {
						title = value;
					} else if (fldName.equals("content")) {
						content = value;
					}
				} else {
					// 取出圖片檔的檔名
					fileName = p.getSubmittedFileName();
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (fileName != null && fileName.trim().length() > 0) {
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					}
				}
			}

		} else {
			System.out.println("此表單不是上傳檔案的表單(RegisterServlet)");
		}

		try {
			Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
			Blob blob = null;
			if (is != null) {
				blob = GlobalService.fileToBlob(is, sizeInBytes);
			}

			Clob clob = GlobalService.stringToClob(content);
//			if (!errorMsg.isEmpty()) {
////				request.setAttribute("mb", mb);
//				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
//				RequestDispatcher rd = request.getRequestDispatcher("/_06_article/addArticle.jsp");
//				rd.forward(request, response);
//				return;
//			}
			// 呼叫ArticleDao的insertArticle方法
			ArticleService service = new ArticleServiceImpl();
			ArticleCategoryBean acb = service.getCategory(categoryTitle, categoryName);
			// 將所有文章資料封裝到ArticleBean(類別的)物件
			ArticleBean ab = new ArticleBean(null, title, authorId, authorName, ts, acb, clob, fileName, blob, 0, "正常",
					null);
			// 如果有錯誤
			service.insertArticle(ab);
//			int n = service.insertArticle(ab);
//			if (n == 1) {
//				response.sendRedirect(getServletContext().getContextPath() + "/_02_login/login.jsp");
//				return;
//			} else {
//				System.out.println("更新此筆資料有誤(RegisterServlet)");
			RequestDispatcher rd = request.getRequestDispatcher("/article/ShowArticleContent?articleId="+ab.getArticleId()+"&filter=false");
			rd.forward(request, response);
			return;
//			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/_06_article/addArticle.jsp");
			rd.forward(request, response);
			return;
		}

	}

}
