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

import _00_init.util.GlobalService;
import _01_register.model.MemberBean;
import _01_register.service.MemberService;
import _07_letter.Service.LetterService;
import _07_letter.model.LetterBean;


@WebServlet("/sendDevil")
public class SendDevilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
		}
				
		MemberBean mb = (MemberBean)session.getAttribute("LoginOK");
		String memberId = mb.getMemberId();
		
		String title = request.getParameter("title");
		System.out.println("title: " + title);
		String content = request.getParameter("content");
		System.out.println("內容: " + content);
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		LetterService letterService = ctx.getBean(LetterService.class);
		
		MemberService memberService = ctx.getBean(MemberService.class);
		//取得現在日期  擺入信件資訊
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sendDay = simpleDateFormat.format(new java.util.Date());
		
		LetterBean lb = new LetterBean(null,title,memberId,content,sendDay,GlobalService.LETTER_TYPE_DEVIL,"n");
		letterService.saveLetter(lb);
		
		mb.setLastSendDate(sendDay);
		memberService.updateMember(mb);
		
		response.sendRedirect(response.encodeRedirectURL(getServletContext().getContextPath() + "/_07_letter/letterInfo") );
	}

}
