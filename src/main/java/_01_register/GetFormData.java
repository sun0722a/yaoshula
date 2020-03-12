package _01_register;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/GetFormData")
public class GetFormData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetFormData() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		/* Setting the Encoding of Input Data */
		request.setCharacterEncoding("UTF-8");

		/* Setting the encoding of the output data to the web page */
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		/* https://www.itread01.com/content/1548753308.html */

        char[] unValidChar = {'_', '-', '@', '#', '$', '!', '&', '*'};
//      讀取使用者所輸入，由瀏覽器送來的 mId 欄位內的資料
		String mid = request.getParameter("mId");
//      String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String Address = request.getParameter("mAddress");
		String iPhone = request.getParameter("iPhone");
		String Email = request.getParameter("Email");
		boolean mail = Email.matches("[a-zA-Z0-9]+@[0-9]+\\.[a-z]+"); 
		System.out.println(mail);
//		request.setAttribute("id", mid);
//      request.setAttribute("password", password);
//		request.setAttribute("passwordCheck", passwordCheck);
		request.setAttribute("address", Address);
		request.setAttribute("iPhone", iPhone);
		request.setAttribute("Email", Email);

//		HttpSession session = request.getSession();
		// 定義存放錯誤訊息的 Collection物件
		Map<String, String> errorMessage = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMessage);
		request.setCharacterEncoding("UTF-8");

//        帳號 密碼抓設定 request.setAttribute("id", mid);
//        Map<String, String> mIdPs = new HashMap<>(); 
//        mIdPs.put("mid",mid);
//        mIdPs.put("password",password);   
//        request.setAttribute("mIdPs", mIdPs);     

		// 讀取使用者所輸入，由瀏覽器送來的 pswd 欄位內的資料，注意大小寫
		String password = request.getParameter("password");
		// 檢查使用者所輸入的資料
		if (password.trim().length() == 0|| password.trim().length() < 8) {
			errorMessage.put("password", "密碼長度不可小於8位數");
		} else if (password.trim().length() > 15) {
			errorMessage.put("password", "密碼長度不能超過15位數");
		}
       
	   //-------------------------------------
		for(char ch : unValidChar) {
			if(mid.contains(Character.toString(ch))) {
				errorMessage.put("id", "帳號不可有特殊符號 _, -, @ , # , $ , ! , & , * ");
			}
		}
//		if(!errorMessage.isEmpty()){
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
//			requestDispatcher.forward(request, response);
//			return;
//		}
		request.setAttribute("id", mid);
       //-------------------------顯示 密碼  錯誤訊息---------------------------------------------------
		if (!errorMessage.isEmpty()) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
			return;
			
		}
//		errorMessage.clear();
		request.setAttribute("password", password);

//-------------------密碼進行比較確認是否相同--------------------------------------------		
		if (!passwordCheck.equals(password)) {
			errorMessage.put("passwordCheck", "密碼不相同請確認!!");
		} 
		if(!errorMessage.isEmpty()){
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		
		request.setAttribute("passwordCheck", passwordCheck);
//--------------------------------------------------------------------
//        地址 改用上面 request.setAttribute("address", Address);簡單寫法 
//        Map<String, String> address = new HashMap<>(); 
//        address.put("mAddress",Address);            
//        request.setAttribute("address", address);

		// 性別
		Map<String, String> genderMap = new HashMap<String, String>();
		genderMap.put("male", "男性");
		genderMap.put("female", "女性");

		// 範例
		Map<Integer, String> langueMap = new HashMap<Integer, String>();
		langueMap.put(0, "C");
		langueMap.put(1, "C++");
		langueMap.put(2, "Java");
		langueMap.put(3, "MATLAB");

		// 生日-----------------------------------------------
		String datepicker = request.getParameter("datepicker");
		System.out.println("datepicker -> " + datepicker);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// yyyy 西元年，MM 月份，dd 日期
		// %tF 同 %tY-%tm-%td 顯示西元年-月-日
		// %tT 同 %tH:%tM:%tS 顯示24小時制的時:分:秒
		Date date = simpleDateFormat.parse(datepicker);
		System.out.println("#1 date -> " + date);
		System.out.printf("#2 date -> %tF %<tT%n", date);
		String date2 = String.format("%tF", date);
		request.setAttribute("date", date2);
		// -----------------------------------------------

		// 性別--------------------------------------------------------
		String gender = request.getParameter("gender");
		System.out.println("gender -> " + gender);
		System.out.println("性別：" + genderMap.get(gender));
		request.setAttribute("gender", genderMap.get(gender));
		// --------------------------------------------------------------
		String langue = request.getParameter("langue");
		int langue2 = Integer.parseInt(langue);
		System.out.println("langue -> " + langue);
		System.out.println("學習的程式語言：" + langueMap.get(langue2));
		request.setAttribute("langue", langueMap.get(langue2));

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("page2.jsp");
		requestDispatcher.forward(request, response);
		return;

	}

}
