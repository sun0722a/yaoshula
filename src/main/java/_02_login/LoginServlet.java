package _02_login;

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

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _00_init.util.GlobalService;
import _01_register.model.MemberBean;
import _01_register.service.MemberService;

/* 未完成: 記住我功能、LoginFilter 跳轉頁面提示 */

//進入登入畫面前會先去LoginFilter看該網頁需不需要登入　再來去FindUserPassword找有沒有cookie留的帳密
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 使用逾時，回首頁
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		
		
		Map<String, String> errorMsgMap = new HashMap<String, String>();

		request.setAttribute("ErrorMsgKey", errorMsgMap);

		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String rm = request.getParameter("rememberMe");

		if (memberId == null || memberId.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");

		}

		if (password == null || password.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}
		if (!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/_02_login/login.jsp");
			rd.forward(request, response);
			return;
		}

		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;

		// 如果記住帳密 打勾 rm字串裡面就不會是null
		if (rm != null) {
			cookieUser = new Cookie("memberId", memberId);
			cookieUser.setMaxAge(30 * 24 * 60 * 60); // cookie存活期一個月
			cookieUser.setPath(request.getContextPath());

			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
//			cookiePassword = new Cookie("password", password);
			cookiePassword.setMaxAge(30 * 24 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rememberMe", "true");
			cookieRememberMe.setMaxAge(30 * 24 * 60 * 60);
			cookieRememberMe.setPath(request.getContextPath());
			// 如果使用者沒有按下記住帳密 就不會保存帳號密碼的cookie
		} else {
			cookieUser = new Cookie("memberId", memberId);
			cookieUser.setMaxAge(0);
			cookieUser.setPath(request.getContextPath());

			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password",encodePassword);
//			cookiePassword = new Cookie("password", password);
			cookiePassword.setMaxAge(0);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rememberMe", "true");
			cookieRememberMe.setMaxAge(0);
			cookieRememberMe.setPath(request.getContextPath());
		}

		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);

//		MemberService memberService = new MemberServiceImpl();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberService memberService = ctx.getBean(MemberService.class);

		password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
		MemberBean mb = null;

		try {
			mb = memberService.checkIdPassword(memberId, password);
			if (mb != null) {
				session.setAttribute("LoginOK", mb);
			} else {
				errorMsgMap.put("LoginError", "帳號或密碼錯誤唷");
			}
		} catch (RuntimeException ex) {
			errorMsgMap.put("LoginError", ex.getMessage());
		}

		if (errorMsgMap.isEmpty()) {
			String contextPath = getServletContext().getContextPath();
			String target = (String) session.getAttribute("target");
			if (target != null) {
				response.sendRedirect(response.encodeRedirectURL(contextPath + target));
			} else {
				response.sendRedirect(response.encodeRedirectURL(contextPath + "/index.jsp"));
			}
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/_02_login/login.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
