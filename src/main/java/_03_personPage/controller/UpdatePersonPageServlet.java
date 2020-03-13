package _03_personPage.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
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

import org.omg.CORBA.RepositoryIdHelper;

import _00_init.util.GlobalService;
import _03_personPage.model.MemberBean;
import _03_personPage.service.MemberService;
import _03_personPage.service.impl.MemberServiceImpl;

/* 等待: 首頁網址、連線逾時、成功訊息 */

/* MultipartConfig的屬性說明: */
//location: 上傳之表單資料與檔案暫時存放在Server端之路徑，此路徑必須存在，否則Web Container將丟出例外。
//fileSizeThreshold: 上傳檔案的大小臨界值，超過此臨界值，上傳檔案會用存放在硬碟，否則存放在主記憶體。
//maxFileSize: 上傳單一檔案之長度限制，如果超過此數值，Web Container會丟出例外
//maxRequestSize: 上傳所有檔案之總長度限制，如果超過此數值，Web Container會丟出例外
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/PersonPage")
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

		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		session.setAttribute("MsgOK", msgOK);

		// 取得原本的使用者資料(id、fileName)
		MemberBean oldMember = (MemberBean) session.getAttribute("LoginOK");
		int id = oldMember.getId();
		String email = "";
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
			// 逐筆讀取使用者輸入資料
			for (Part p : parts) {
				String fldName = p.getName(); // 取得欄位名稱(name)
				String value = request.getParameter(fldName); // 取得欄位值(value)
				System.out.println("fldName= " + fldName + "value= " + value);

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
						address += value;
					} else if (fldName.equals("district")) {
						address += value;
					} else if (fldName.equals("address")) {
						address += value;
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
			System.out.println("此表單不是上傳檔案的表單");
		}
		try {
			// 將圖片轉換成Blob
			if (is != null) {
				blob = GlobalService.fileToBlob(is, sizeInBytes);
			}
			// 將可更改的會員資料封裝到MemberBean
			MemberBean mem = new MemberBean(id, null, null, null, null, email, phone, address, fileName, blob, null,
					null, null);
			// 呼叫MemberDao的updateMember方法(經由MemberService)
			MemberService service = new MemberServiceImpl();
			int n = service.updateMember(mem);
			// 更新session內的使用者資料
			MemberBean mb = service.queryMember(id);
			session.setAttribute("LoginOK", mb);
			// 如果更新列數為1(updateMember的傳回值) => 成功
			if (n == 1) {
				msgOK.put("UpdateOK", "<Font color='red'>更新成功</Font>");
			} else {
				System.out.println("更新此筆資料有誤(UpdatePersonPageServlet)");
			}

			// 轉換頁面
			response.sendRedirect(getServletContext().getContextPath() + "/_03_personPage/personPage.jsp");
			return;

		} catch (Exception e) {
			System.out.println("UpdatePersonPageServlet類別的#fileToBlob()例外: " + e.getMessage());
			response.sendRedirect(getServletContext().getContextPath() + "/_03_personPage/personPage.jsp");
		}
	}
}
