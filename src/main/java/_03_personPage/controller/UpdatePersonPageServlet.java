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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import _00_init.util.GlobalService;
import _03_personPage.model.MemberBean;
import _03_personPage.service.MemberService;
import _03_personPage.service.impl.MemberServiceImpl;

@WebServlet("/_03_personPage/UpdatePersonPage.do")
public class UpdatePersonPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼

		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();

		HttpSession session = request.getSession(false);
		if (session == null) { // 使用逾時，回首頁
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		request.setAttribute("MsgError", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息

		String email = "";
		String address = "";
		String phone = "";
		String fileName = "";
		long sizeInBytes = 0;
		InputStream is = null;
		// 取出HTTP request內所有的parts
		Collection<Part> parts = request.getParts();
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);

				// 1. 讀取使用者輸入資料
				if (p.getContentType() == null) {
					if (fldName.equals("memberId")) {
						email = value;
					} else if (fldName.equals("password")) {
						address = value;
					} else if (fldName.equals("password1")) {
						phone = value;
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
			errorMsg.put("errTitle", "此表單不是上傳檔案的表單");
		}

		if (!errorMsg.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("/_03_personPage/personPage.jsp");
			rd.forward(request, response);
			return;
		}
		try {
			// 4. 產生MemberDao物件，以便進行Business Logic運算
			Blob blob = null;
			if (is != null) {
				blob = GlobalService.fileToBlob(is, sizeInBytes);
			}
			// 將所有會員資料封裝到MemberBean
			MemberBean oldMember = (MemberBean) session.getAttribute("LoginOK");
			int id = oldMember.getId();
			MemberBean mem = new MemberBean(id, null, null, null, null, email, phone, address, fileName, blob, null,
					null, null);
			// 呼叫MemberDao的updateMember方法
			MemberService service = new MemberServiceImpl();
			int n = service.updateMember(mem);
			//如果更新列數為1 => 成功
			if (n == 1) {
				msgOK.put("UpdateOK", "<Font color='red'>更新成功</Font>");
				response.sendRedirect("/_03_personPage/personPage.jsp");
				return;
			} else {
				errorMsg.put("UpdateError", "更新此筆資料有誤(UpdatePersonPageServlet)");
			}

			// 5.依照 Business Logic 運算結果來挑選適當的畫面
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("/_03_personPage/personPage.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("UpdateErrorException", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/_03_personPage/personPage.jsp");
			rd.forward(request, response);
		}
	}
}
