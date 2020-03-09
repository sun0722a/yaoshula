package member.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _00_init.util.GlobalService;
import _03_personPage.model.MemberBean;
import _03_personPage.service.MemberService;
import _03_personPage.service.impl.MemberServiceImpl;

@WebServlet("/_01_login/login.do")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		Map<String,String> errorMsgMap = new HashMap<String,String>();
		
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("pswd");
		String rm = request.getParameter("rememberMe");
		String requestURI = (String) session.getAttribute("requestURI");
		
		if(userId == null || userId.trim().length() == 0){
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
			
		}
		if(password == null || password.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}
		if(!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		
		//如果記住帳密 打勾 rm字串裡面就不會是null
		if(rm != null) {
			cookieUser = new Cookie("user", userId);
			cookieUser.setMaxAge(30*24*60*60);  //cookie存活期一個月
			cookieUser.setPath(request.getContextPath());
			
			
			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(30 * 24 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());
			//如果使用者沒有按下記住帳密 就不會保存帳號密碼的cookie
		} else {
			cookieUser = new Cookie("user",userId);
			cookieUser.setMaxAge(0);
			cookieUser.setPath(request.getContextPath());
			
			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password",encodePassword);
			cookiePassword.setMaxAge(0);
			cookiePassword.setPath(request.getContextPath());
			
			cookieRememberMe = new Cookie("rm","true");
			cookieRememberMe.setMaxAge(0);
			cookieRememberMe.setPath(request.getContextPath());
		}
		
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);
			
		MemberService memberService = new MemberServiceImpl();
		
		String tmp = GlobalService.encryptString(password);
		password = GlobalService.getMD5Endocing(tmp);
		MemberBean mb = null;
		
		try {
			mb = memberService.checkIdPassword(userId, password);
			if(mb!= null) {
				session.setAttribute("LoginOK", mb);
			}else {
				errorMsgMap.put("LoginError", "帳號或密碼錯誤唷");
			}
		}catch(RuntimeException ex) {
			errorMsgMap.put("LoginError", ex.getMessage());
		}
		
		if(errorMsgMap.isEmpty()) {
			if(requestURI != null) {
				requestURI = (requestURI.length() == 0 ? request.getContextPath() : requestURI);
				response.sendRedirect(response.encodeRedirectURL(requestURI));
				return;
			}else {
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
				return;
			}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
