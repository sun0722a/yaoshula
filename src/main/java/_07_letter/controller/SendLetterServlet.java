package _07_letter.controller;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;

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


@WebServlet("/letter/sendDriftLetter")
public class SendLetterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
		}
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		
		
		Integer writerId = mb.getId();
		String  writerName = mb.getMemberId();
		
		
		String letterTitle = request.getParameter("letterTitle");
		String letterCategory = request.getParameter("letterCategory");
		String letterContent = request.getParameter("content");
		System.out.println("writerId" + writerId);
		System.out.println("writerName" + writerName);
		System.out.println("letterTitle" + letterTitle);
		System.out.println("letterCategory" + letterCategory);
		System.out.println("letterContent" + letterContent);
		String Letterstatus = "n";
		try {
			Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			LetterService letterService = ctx.getBean(LetterService.class);
			Clob clobContent = GlobalService.stringToClob(letterContent);
			LetterBean lb = new LetterBean(null, letterTitle, writerName, ts, clobContent, letterCategory, null, null, Letterstatus, null);
			letterService.saveLetter(lb);
			System.out.println("寄信成功");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			return;
		} catch (IOException e) {	
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

	

}
