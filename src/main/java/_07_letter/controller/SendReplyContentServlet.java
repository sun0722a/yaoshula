package _07_letter.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

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
import _07_letter.Service.LetterService;
import _07_letter.model.LetterBean;


@WebServlet("/sendReplyContent")
public class SendReplyContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
		}
		
		//取得回覆者的memberId
		MemberBean mb = (MemberBean)session.getAttribute("LoginOK");
		
		String replyierId = mb.getMemberId();
		String letterIdStr = request.getParameter("id");
		String replyContent = request.getParameter("replyContent");
		Integer letterId = Integer.parseInt(letterIdStr);
		
		
		System.out.println("letterId" +letterId);
		System.out.println("content" + replyContent);
		
		try {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		LetterService letterService = ctx.getBean(LetterService.class);
		MemberService memberService = ctx.getBean(MemberService.class);
		LetterBean lb = letterService.getLetter(letterId);
		//把資訊存入原本的信件裡
		lb = new LetterBean(letterId,replyierId,replyContent,"y");
		letterService.updateReply(lb);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String replyDay = simpleDateFormat.format(new java.util.Date());
		mb.setLastReplyDate(replyDay);
		memberService.updateMember(mb);
		response.sendRedirect(response.encodeRedirectURL(getServletContext().getContextPath() + "/_07_letter/letterInfo") );
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
