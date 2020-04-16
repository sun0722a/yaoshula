package _07_letter.controller;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
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


//寄信Servlet
@WebServlet("/letter/sendLetter")
public class SendLetterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
		}
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		System.out.println(mb.getMemberId());
		//先檢查當日有沒有寄過信 沒有的話直接下一頁 有的話留在原頁(可能再加按鈕不能按之類的)
		String lastSendDate = mb.getLastSendDate();
		System.out.println("上次寄信日期:" + lastSendDate);
//		if(lastSendDate == null) {
//			RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/sendLetter.jsp");
//			rd.forward(request, response);
//			return;
//		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpleDateFormat.format(new java.util.Date());
		String memberId = mb.getMemberId();
		System.out.println(today);
		if(lastSendDate != null && lastSendDate.equals(today)) {
			System.out.println("已經寄過信了，無法再寄");
			RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/driftLetter.jsp");
			rd.forward(request, response);
			return;
			
		}else {
			System.out.println("可寄");
//			String contextPath = getServletContext().getContextPath();
//			response.sendRedirect(response.encodeRedirectURL(contextPath + "/_07_letter/sendLetter.jsp"));
			RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/sendLetter.jsp");
			rd.forward(request, response);
			return;
		}
	
	}


	@Override  //POST方法用在信寄出時
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
		}
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		
		
		Integer writerId = mb.getId();
		String  memberId = mb.getMemberId();
		
		
		String letterTitle = request.getParameter("letterTitle");
		String letterCategory = request.getParameter("letterCategory");
		String letterContent = request.getParameter("content");
		System.out.println("信件ID: " + writerId);
		System.out.println("寄信人ID: " + memberId);
		System.out.println("信件標題: " + letterTitle);
		System.out.println("希望的回覆類型: " + letterCategory);
		System.out.println("信件內容: " + letterContent);
		String Letterstatus = "n";
		
		try {
			Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			LetterService letterService = ctx.getBean(LetterService.class);
			
			MemberService memberService = ctx.getBean(MemberService.class);
			Clob clobContent = GlobalService.stringToClob(letterContent);
								
			//儲存信件
			LetterBean lb = new LetterBean(null, letterTitle, memberId, ts, clobContent, letterCategory, null, null, Letterstatus, null);
			letterService.saveLetter(lb);
			
			//更新寄信日期
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sendToday = simpleDateFormat.format(new java.util.Date());
//			memberService.updateSendDate(memberId, sendToday);
			mb.setLastSendDate(sendToday);
			memberService.updateMember(mb);
			System.out.println("寄信時間" + sendToday);
			
			System.out.println("寄信成功");
//			session.setAttribute("mb", mb);
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
