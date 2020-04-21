package _02_login;

import java.io.IOException;

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



@WebServlet("/changepswd")
public class ModifyPswdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//第二步 得到連結後 比對搜尋字串是否為一樣 如果為一樣就假設他為會員 給予mb物件 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		
		//取得搜尋字串
		String emailVerifyCode = request.getParameter("emailCode");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		//利用getBean取得MemberService 型別的物件
		MemberService memberService = ctx.getBean(MemberService.class);
		
		MemberBean mb = null;
		System.out.println("驗證碼為" + emailVerifyCode);
		//透過service的方法得到與驗證碼相同的MemberBean物件
		mb =  memberService.getEmailValid(emailVerifyCode);
		session.setAttribute("mb",mb);
		if(mb != null) {
		
		String ContextPath = getServletContext().getContextPath();
		response.sendRedirect(response.encodeRedirectURL(ContextPath + "/_02_login/enterNewPassword.jsp"));
		}
	}
	
	
	//第三步 改密碼 取得新的密碼後再加密透過Service方法傳回資料庫修改
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		
		String password = request.getParameter("password");
		System.out.println(password);
		
		MemberBean mb = (MemberBean) session.getAttribute("mb");
		String memberId = mb.getMemberId();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberService memberService = ctx.getBean(MemberService.class);
		
		String passwordNew = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
		
		int n = memberService.updateMemberPassword(memberId, passwordNew);
		System.out.println(n);
		if(n == 1) {
			System.out.println("修改成功");
			
			
		}else {
			System.out.println("修改失敗");
		}
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(response.encodeRedirectURL(contextPath + "/_02_login/login.jsp"));
		
	}

}
