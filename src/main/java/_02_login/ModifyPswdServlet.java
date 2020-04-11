package _02_login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _01_register.model.MemberBean;
import _01_register.service.MemberService;


//第二步 得到連結後 比對搜尋字串是否為一樣 如果為一樣就假設他為會員 給予mb物件 
@WebServlet("/changepswd")
public class ModifyPswdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
//		StringBuilder content = new StringBuilder();
		//取得信件內連結的queryString
		String queryString = request.getQueryString();
		
		//把搜尋字串前面的字拿掉，取得後面的字串方便與資料庫進行比對
		String emailVerifyCode = queryString.replaceAll("emailCode=", "");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		//利用getBean取得MemberService 型別的物件
		MemberService memberService = ctx.getBean(MemberService.class);
		
		MemberBean mb = null;
		System.out.println(emailVerifyCode);
		//透過service的方法得到與驗證碼相同的MemberBean物件
		mb =  memberService.getEmailValid(emailVerifyCode);
		session.setAttribute("mb",mb);
		if(mb != null) {
		
		String ContextPath = getServletContext().getContextPath();
		response.sendRedirect(response.encodeRedirectURL(ContextPath + "/_02_login/enterNewPassword.jsp"));
		}
	}

}
