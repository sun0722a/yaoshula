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

import _00_init.util.GlobalService;
import _01_register.model.MemberBean;
import _01_register.service.MemberService;
import _07_letter.Service.LetterService;
import _07_letter.model.LetterBean;


@WebServlet("/replyDevil")
public class ReplyDevilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
		}
		
		//先拿memberId
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		String memberId = mb.getMemberId();

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		LetterService letterService = ctx.getBean(LetterService.class);
		MemberService memberService = ctx.getBean(MemberService.class);
		
		//看mb物件裡有沒有當日信件
		LetterBean lb = null;
		Integer letterIdoftheDay = mb.getLetterOftheDay();
		if(letterIdoftheDay == null) {
			
			
			//隨機取得信件編號的index值
			Map<Integer,LetterBean> letterMap = letterService.getUnfinishedLetter(memberId,"惡魔", "n");
			Integer mapSize = letterMap.size();
			Set<Integer> letterNo = letterMap.keySet();
			System.out.println("有" + mapSize + " 封" + "信件編號為" + letterNo);
		
			try {
				int randomNo = (int)(Math.random() * mapSize);
				Integer letterId = (Integer)letterNo.toArray()[randomNo];
				System.out.println("隨機index值:" + randomNo + "隨機數的key:" + letterId);
				
				lb = letterService.getLetter(letterId);
				session.setAttribute("lb", lb);
				
				//更新信件狀態 讓信件無法被其他會員取得  會員在同一天也只能拿到同樣的一封
				mb.setLetterOftheDay(letterId);
				memberService.updateLetterOftheDay(memberId, letterId);
				letterService.updateLetterOccupied(letterId, GlobalService.LETTER_STATUS_OCCUPIED);
				
				
				System.out.println("信件作者:" + lb.getLetterWriter());
				System.out.println("回覆類型: " + lb.getLetterCategory());
				System.out.println("內容" + lb.getLetterContent());
				System.out.println("title:" + lb.getLetterTitle());
				System.out.println("信件狀態:" + lb.getStatus());
				
				RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/replyDevil.jsp");
				rd.forward(request, response);
				return;
				
			}catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("無惡魔信可回");
					session.setAttribute("noLetters", "noLetters");
					RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/letterInfo.jsp");
					rd.forward(request, response);
					return;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			//如果會員表格內已經有每天更新一次的信件ID 就會直接去撈取該信件
		}else {
			
			lb = letterService.getLetter(letterIdoftheDay);
			session.setAttribute("lb", lb);
			System.out.println("信件作者:" + lb.getLetterWriter());
			System.out.println("內容" + lb.getLetterContent());
			System.out.println("title:" + lb.getLetterTitle());
			RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/replyDevil.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
