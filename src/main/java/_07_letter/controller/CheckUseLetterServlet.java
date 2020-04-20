package _07_letter.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;


@WebServlet("/checkUseLetter")
public class CheckUseLetterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
		}
		
		Writer os = null;
		os = response.getWriter();
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		System.out.println(mb.getMemberId());
		
		String lastSendDate = mb.getLastSendDate();
		System.out.println("上次寄信日期:" + lastSendDate);
		
		String lastReplyDate = mb.getLastReplyDate();
		System.out.println("上次回信日期:" + lastReplyDate);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpleDateFormat.format(new java.util.Date());
		
		if(lastReplyDate == null) {
			os.write("可回");
			os.close();
		}
		if(lastReplyDate != null && lastReplyDate.equals(today)) {
			os.write("不可回");
			os.close();
		}else {
			os.write("可回");
			os.close();
		}
	}

}
