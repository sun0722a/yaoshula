package _07_letter.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;

//漂流信的首頁 先檢查有沒有寄過信
@WebServlet("/_07_letter/letterInfo")
public class letterInfoServlet extends HttpServlet {
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
		
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		System.out.println("memberId : " + mb.getMemberId());
		
		String lastSendDate = mb.getLastSendDate();
//		System.out.println("上次寄信日期:" + lastSendDate);
		
		String lastReplyDate = mb.getLastReplyDate();
//		System.out.println("上次回信日期:" + lastReplyDate);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpleDateFormat.format(new java.util.Date());
//		System.out.println("今天日期" + today);

		//如果當天寄過信或是寄信欄是不是空的 就不能寄
		if(lastSendDate != null && lastSendDate.equals(today)) {
			System.out.println("不能寄信");
			session.setAttribute("sendError", "不能寄信");
				
		}
		
		//如果當天回過信或是回信欄不是空的 就不能回
		if(lastReplyDate != null && lastReplyDate.equals(today)) {
			System.out.println("不能回信");
			session.setAttribute("replyError", "不能回信");
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/letterInfo.jsp");
		rd.forward(request, response);
		return;
		
	}

}
