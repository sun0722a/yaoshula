//package _07_letter.controller;
//
//import java.io.IOException;
//import java.util.LinkedHashSet;
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import _00_init.util.GlobalService;
//import _07_letter.Service.LetterService;
//import _07_letter.model.LetterBean;
//
//
//@WebServlet("/letter/getLetters")
//public class GetLettersServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//   
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		
//		HttpSession session = request.getSession(false);
//		
//		if(session == null) {
//			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
//		}
//		
//		String queryString = request.getQueryString();
//		String letterIdStr = queryString.replaceAll("id=", "");
//
//		
//		Integer letterId = Integer.parseInt(letterIdStr);
//		System.out.println("letterId: " + letterId);
//		
//		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//		LetterService letterService = ctx.getBean(LetterService.class);
//		
//		LetterBean lb = letterService.getLetter(letterId);
//		String content="";
//		String reply="";
//		
//		try {
//				content = GlobalService.clobToString(lb.getLetterContent());
//				reply = GlobalService.clobToString(lb.getReplyContent());
//				session.setAttribute("content", content);
//				session.setAttribute("reply", reply);	
//			System.out.println(content);
//			System.out.println(reply);
//			RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/wholeLetter.jsp");
//			rd.forward(request, response);
//			return;
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
//	
//}
	
