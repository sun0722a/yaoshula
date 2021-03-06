package _00_init.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _06_article.model.ArticleBean;
import _06_article.service.ArticleService;

/* 未完成: 預設圖片  */

@WebServlet("/init/getArticleImage")
public class GetArticleImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OutputStream os = null;
		InputStream is = null;
		String fileName = null;
		String mimeType = null;
		Blob blob = null;
		try {
			// 讀取瀏覽器傳送來的主鍵
			String idStr = request.getParameter("id");
			int id = 0;
			try {
				id = Integer.parseInt(idStr);
			} catch (NumberFormatException e) {
				;
			}

			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			ArticleService articleService = ctx.getBean(ArticleService.class);

			// 利用主鍵取得bean物件及其內容
			ArticleBean bean = articleService.getArticle(id);
			if (bean != null) {
				blob = bean.getImage();
				if (blob != null) {
					is = blob.getBinaryStream();
				}
				fileName = bean.getFileName();
			}
			// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)
			if (is == null) {
				fileName = "NoImage.jpg";
				is = getServletContext().getResourceAsStream("/image/_03_personPage/" + fileName);
			}

			// 由圖片檔的檔名來得到檔案的MIME型態
			mimeType = getServletContext().getMimeType(fileName);
			// 設定輸出資料的MIME型態
			response.setContentType(mimeType);
			// 取得能寫出非文字資料的OutputStream物件
			os = response.getOutputStream();
			// 由InputStream讀取位元組，然後由OutputStream寫出
			int len = 0;
			byte[] bytes = new byte[8192];
			while ((len = is.read(bytes)) != -1) {
				os.write(bytes, 0, len);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(
					"_00_init.util.GetArticleImageServlet#doGet()發生SQLException: " + ex.getMessage());
		} finally {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
		}
	}
}