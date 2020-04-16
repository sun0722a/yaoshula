package _02_login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _00_init.util.GlobalService;
import _00_init.util.SendEmail;
import _01_register.model.MemberBean;



//忘記密碼第一步 先輸入email 並去信箱點取連結
@WebServlet("/findPassword")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	//會員認證用的
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
				String memberEmailStr = request.getParameter("email");
		
				MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
				System.out.println(mb.getMemberId());
		
					
				System.out.println(memberEmailStr);
				String authToken = GlobalService.getMD5Endocing(GlobalService.encryptString(memberEmailStr));
				
				String subject = null;
				StringBuilder content = new StringBuilder();
				String[] memberEmail = {memberEmailStr};
				subject = "歡迎你加入要抒啦的會員";
				content.setLength(0);
				content.append("<p>" + "請點選以下連結" + "</p>" + "<br>" + 
				GlobalService.DOMAIN_PATTERN + "/EmailVerify" + "?" + "emailCode=" + authToken  +"<br>"
				+"<p>" + "進入連結後即認證成功，可以去抒發一下了!" + "</p>");
							
				Thread sendEmail = new SendEmail(memberEmail, subject,content.toString(),"");
				System.out.println(memberEmail[0]);
				sendEmail.start();
				
				session.removeAttribute("LoginOK");
				response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
				return;
		
			
	}
	
	//忘記密碼用的
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(false);
			if(session == null) {
				response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
				return;
			}	
					String memberEmailStr = request.getParameter("email");
					System.out.println(memberEmailStr);
					
					String[] memberEmail = {memberEmailStr};
					String authToken = GlobalService.getMD5Endocing(GlobalService.encryptString(memberEmailStr));
					String subject = null;
					StringBuilder content = new StringBuilder();
					
					subject = "請點選連結修改密碼";
					content.setLength(0);
					content.append("<p>" + "請點選以下連結修改密碼" + "</p>" + "<br>" + 
					GlobalService.DOMAIN_PATTERN + "/changepswd" + "?" + "emailCode=" + authToken  +"<br>"
					+"<p>" + "下次不要再弄丟密碼了啦" + "</p>");
								
					Thread sendEmail = new SendEmail(memberEmail, subject,content.toString(),"");
					System.out.println(memberEmail[0]);
					sendEmail.start();
					
					response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
					return;
				
	}
			
	

}
