package _07_letter.controller;

import java.io.IOException;
import java.util.List;

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
import _07_letter.Service.LetterService;
import _07_letter.model.LetterBean;


@WebServlet("/_07_letter/myletters")
public class GetLettersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
		}
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		String memberId = mb.getMemberId();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		LetterService letterService = ctx.getBean(LetterService.class);
		List<LetterBean> letters=  letterService.getAllLettersByMemberSend(memberId, GlobalService.LETTER_STATUS_DONE);
		session.setAttribute("letters", letters);
		
		RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/letters.jsp");
		rd.forward(request, response);
		return;
	}

}
