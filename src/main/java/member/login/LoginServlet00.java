package member.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_personPage.model.MemberBean;

@WebServlet("/member/login.do")
public class LoginServlet00 extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		
		String userId = request.getParameter("UserId");
		String password = request.getParameter("password");
		
		
		if(userId == null || userId.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
		}
		
		if(password == null || password.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}
		
		if(!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/login/login.jsp");
			rd.forward(request, response);
			return;
		}
		
//		LoginService ls = new LoginService();
//		
//		MemberBean mb = ls.checkIDPassword(userId, password);
//		
//		if(mb!= null) {
//			session.setAttribute("LoginOK", mb);
//		}else {
//			errorMsgMap.put("LoginError", "帳號或密碼錯誤");
//		}
		
		if(errorMsgMap.isEmpty()) {
			String contextPath = getServletContext().getContextPath();
			String target = (String) session.getAttribute("target");
			if(target !=null) {
				response.sendRedirect(response.encodeRedirectURL(contextPath + target));
				
			}else {
				response.sendRedirect(response.encodeRedirectURL(contextPath + "loginSuccess.jsp"));
			}
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/login/login.jsp");
			rd.forward(request, response);
			return;
		}
	}
}
