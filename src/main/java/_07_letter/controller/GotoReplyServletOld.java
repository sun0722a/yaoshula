package _07_letter.controller;

import java.io.IOException;
import java.sql.Clob;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _00_init.util.GlobalService;
import _01_register.model.MemberBean;
import _01_register.service.MemberService;
import _07_letter.Service.LetterService;
import _07_letter.model.LetterBean;

@WebServlet("/letter/goToReply")
public class GotoReplyServletOld extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		
		if(session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
		}
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		LetterBean lb = (LetterBean) session.getAttribute("letter");
		
		Integer letterId = lb.getLetterId();
		System.out.println("letterId " + letterId);
		String memberId = mb.getMemberId();
		String replyContent = request.getParameter("replyContent");
		
		
		
		try {
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			LetterService letterService = ctx.getBean(LetterService.class);
			MemberService memberService = ctx.getBean(MemberService.class);
			
			lb = new LetterBean(letterId,memberId,replyContent,"y");
			letterService.updateReply(lb);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String replyToday = simpleDateFormat.format(new java.util.Date());
			mb.setLastReplyDate(replyToday);
//			memberService.updateReplyDate(memberId, replyToday);
			memberService.updateMember(mb);
			System.out.println("回信成功" + " 回信日期" + replyToday);
//			session.setAttribute("mb", mb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String letterStatus = lb.getStatus();
		System.out.println("狀態為:" + letterStatus);
		
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		return;
	}

	

}
