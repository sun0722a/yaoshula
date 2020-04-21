package _07_letter.controller;

import java.io.IOException;
import java.sql.Clob;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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


@WebServlet("/letter/getReply")
public class GetReplyServletOld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setCharacterEncoding("UTf-8");
			
			HttpSession session = request.getSession(false);
			
			if(session == null) {
				response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			}
			
			MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
			
			String memberId = mb.getMemberId();
			System.out.println("memberID= "+ memberId);
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			LetterService letterService = ctx.getBean(LetterService.class);
			
			Map<Integer,LetterBean> letterMap = new  LinkedHashMap<Integer, LetterBean>();
//			letterMap = letterService.getAllLettersByMemberSend(memberId,"y");
			System.out.println("有幾封完整信件:" + letterMap.size());
			Set<Integer> allLetterId = letterMap.keySet();
			System.out.println("信件id的SET" + allLetterId);
			Integer letterId ;
			String title = "";
			
			String content = "";
			String replyContent="";
			
			LetterBean lb = null;
			try {
			for(Integer n: allLetterId) {
				lb = letterMap.get(n);
				System.out.println("id" + lb.getLetterId() + ",title:" + lb.getLetterTitle());
				letterId = lb.getLetterId();
				title = lb.getLetterTitle();
				content = lb.getLetterContent();
				replyContent = lb.getReplyContent();
				
			}
				System.out.println(content);
				System.out.println(replyContent);
				
				
				session.setAttribute("letter", letterMap);
//				session.setAttribute("content", content);
//				session.setAttribute("replyContent", replyContent);
				RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/wholeLetter.jsp");
				rd.forward(request, response);
				return;
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
	}

}
