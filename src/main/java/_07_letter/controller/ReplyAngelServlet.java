package _07_letter.controller;

import java.io.IOException;
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

import _01_register.model.MemberBean;
import _01_register.service.MemberService;
import _07_letter.Service.LetterService;
import _07_letter.model.LetterBean;

@WebServlet("/replyAngel")
public class ReplyAngelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
		}
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		String memberId = mb.getMemberId();
		

		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		LetterService letterService = ctx.getBean(LetterService.class);
		MemberService memberService = ctx.getBean(MemberService.class);
		
		LetterBean lb = null;
		//去看當天有沒有撈過信了 有的話直接前往
		Integer letterIdoftheDay = mb.getLetterOftheDay();
		//如果選擇的是天使主題
		
		if (letterIdoftheDay == null) {
			Map<Integer, LetterBean> letterMap = letterService.getUnfinishedLetter(memberId,"天使", "n");

			Integer mapSize = letterMap.size();
			Set<Integer> letterNo = letterMap.keySet();

			// 隨機取得信件編號的index值
			try {
				int randomNo = (int) (Math.random() * mapSize);
				Integer letterId = (Integer) letterNo.toArray()[randomNo];
				
				System.out.println("隨機index值:" + randomNo + "隨機數的key:" + letterId);
				lb = letterService.getLetter(letterId);
				
				//把取得的letterId傳進mb物件裡面並更新 
				mb.setLetterOftheDay(letterId);
				memberService.updateLetterOftheDay(memberId, letterId);
				
				session.setAttribute("lb", lb);
				System.out.println("信件作者:" + lb.getLetterWriter());
				System.out.println("內容" + lb.getLetterContent());
				System.out.println("title:" + lb.getLetterTitle());
				
				RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/replyAngel.jsp");
				rd.forward(request, response);
				return;

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("無天使信可回");
				session.setAttribute("noLetters", "noLetters");
				RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/letterInfo.jsp");
				rd.forward(request, response);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
				
				
		}else {
			lb = letterService.getLetter(letterIdoftheDay);
			session.setAttribute("lb", lb);
			System.out.println("信件作者:" + lb.getLetterWriter());
			System.out.println("內容" + lb.getLetterContent());
			System.out.println("title:" + lb.getLetterTitle());
			System.out.println("type:" + lb.getLetterCategory());
			RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/replyAngel.jsp");
			rd.forward(request, response);
			return;
			
			
		}
		
		
		

	}
}
