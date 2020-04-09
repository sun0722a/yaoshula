package _03_personPage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _00_init.util.GlobalService;
import _01_register.model.MemberBean;
import _01_register.service.MemberService;

/* 待測試: 連線逾時 */

/* MultipartConfig的屬性說明: */
//location: 上傳之表單資料與檔案暫時存放在Server端之路徑，此路徑必須存在，否則Web Container將丟出例外。
//fileSizeThreshold: 上傳檔案的大小臨界值，超過此臨界值，上傳檔案會用存放在硬碟，否則存放在主記憶體。
//maxFileSize: 上傳單一檔案之長度限制，如果超過此數值，Web Container會丟出例外
//maxRequestSize: 上傳所有檔案之總長度限制，如果超過此數值，Web Container會丟出例外
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/personPage/updatePersonPage")
public class UpdatePersonPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		// 取得原本的使用者資料(id、fileName)
		MemberBean oldMember = (MemberBean) session.getAttribute("LoginOK");
		int id = oldMember.getId();
		String email = "";
		String city = "";
		String area = "";
		String address = "";
		String phone = "";
		String fileName = oldMember.getFileName();
		Blob blob = oldMember.getPicture();
		long sizeInBytes = 0;
		InputStream is = null;

		// 取出HTTP request內所有的parts
		Collection<Part> parts = request.getParts();
		// 由parts != null來判斷此上傳資料是否為上傳資料的表單(HTTP multipart request)
		if (parts != null) {
			// 逐項讀取使用者輸入資料
			for (Part p : parts) {
				String fldName = p.getName(); // 取得欄位名稱(name)
				String value = request.getParameter(fldName); // 取得欄位值(value)

				if (p.getContentType() == null) {
					if (fldName.equals("cancel")) {
						response.sendRedirect(getServletContext().getContextPath() + "/_03_personPage/personPage.jsp");
						return;
					}
					if (fldName.equals("email")) {
						email = value;
					} else if (fldName.equals("phone")) {
						phone = value;
					} else if (fldName.equals("county")) {
						city = value;
					} else if (fldName.equals("district")) {
						area = value;
					} else if (fldName.equals("address")) {
						address = value;
					}
				} else { // p.getContentType() = application/octet-stream
					// 如果有選擇圖片 => 取得檔名&inputStream
					if (p.getSubmittedFileName().trim().length() != 0) {
						// 取出圖片檔的檔名
						fileName = p.getSubmittedFileName();
						// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
						fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					}
				}
			}
		} else {
			System.out.println("此表單不是上傳檔案的表單(UpdatePersonPageServlet)");
		}
		try {
			// 將圖片轉換成Blob
			if (is != null) {
				blob = GlobalService.fileToBlob(is, sizeInBytes);
			}
			// 將可更改的會員資料封裝到MemberBean

			MemberBean mem = new MemberBean(id, null, null, null, null, email, phone, city, area, address, fileName,
					blob, null, null, null, null, null);

			// 呼叫MemberDao的updateMember方法(經由MemberService)
//			MemberService service = new MemberServiceImpl();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			MemberService service = ctx.getBean(MemberService.class);
			int n = service.updateMember(mem);
			// 更新session內的使用者資料
			MemberBean mb = service.queryMember(id);
			session.setAttribute("LoginOK", mb);
			// 如果更新列數不為1(updateMember的傳回值) => 失敗
			if (n != 1) {
				System.out.println("更新此筆資料有誤(UpdatePersonPageServlet)");
			}

			// 轉換頁面
			response.sendRedirect(getServletContext().getContextPath() + "/_03_personPage/personPage.jsp");
			return;

		} catch (Exception e) {
			System.out.println("UpdatePersonPageServlet類別的#fileToBlob()例外: " + e.getMessage());
			response.sendRedirect(getServletContext().getContextPath() + "/_03_personPage/personPage.jsp");
			return;
		}
	}
}
