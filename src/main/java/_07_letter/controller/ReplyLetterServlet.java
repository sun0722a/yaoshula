package _07_letter.controller;

import java.io.IOException;
import java.sql.Clob;
import java.text.SimpleDateFormat;
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


@WebServlet("/letter/replyLetter")
public class ReplyLetterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
		}
		
		
		
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		String memberId = mb.getMemberId();
		//一樣檢查當日有沒有回過信
		String lastReplyDate = mb.getLastReplyDate();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpleDateFormat.format(new java.util.Date());
		System.out.println(memberId);
		System.out.println("今天日期:" + today);
		System.out.println("上次回信日期:" + lastReplyDate);
		if(lastReplyDate == null) {
			System.out.println("還沒回過");
		}
		if(lastReplyDate != null && lastReplyDate.equals(today)) {
			System.out.println("已經回過信了，無法再回");
			RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/driftLetter.jsp");
			rd.forward(request, response);
			return;

		}else {
			System.out.println("還沒回過");
		}
		
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		LetterService letterService = ctx.getBean(LetterService.class);
		String replyType = request.getParameter("type");

		LetterBean lb = null;

		//天使回覆
			if(replyType.equals("天使")) {
			Map<Integer,LetterBean> letterMap = letterService.getUnfinishedLetter("天使", "n");
			
			Integer mapSize = letterMap.size();
			Set<Integer> letterNo = letterMap.keySet();
			
			//隨機取得信件編號的index值
			try {
				int randomNo = (int)(Math.random() * mapSize);
				Integer letterId = (Integer)letterNo.toArray()[randomNo];
				
				System.out.println("隨機index值:" + randomNo + "隨機數的key:" + letterId);
				lb = letterService.getLetter(letterId);
				String content = lb.getLetterContent();
				String title = lb.getLetterTitle();
				
				session.setAttribute("letter", lb);
				session.setAttribute("content", content);
				session.setAttribute("title", title);
				session.setAttribute("type", replyType);

				RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/replyLetter.jsp");
				
				rd.forward(request, response);
				return;
			}catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("無天使信可回");
					RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/driftLetter.jsp");
					rd.forward(request, response);
					return;
						}catch (Exception e) {
							e.printStackTrace();
						}		
					
			//惡魔回覆
			}else {
				System.out.println("回覆類型: " + replyType);
				Map<Integer,LetterBean> letterMap = letterService.getUnfinishedLetter("惡魔", "n");
				Integer mapSize = letterMap.size();
				Set<Integer> letterNo = letterMap.keySet();
				System.out.println("有" + mapSize + " 封" + "信件編號為" + letterNo);
				//隨機取得信件編號的index值
				try {
					int randomNo = (int)(Math.random() * mapSize);
					Integer letterId = (Integer)letterNo.toArray()[randomNo];
					System.out.println("隨機index值:" + randomNo + "隨機數的key:" + letterId);
					lb = letterService.getLetter(letterId);
					String content= lb.getLetterContent();
					String title = lb.getLetterTitle();
					
					session.setAttribute("lb", lb.getLetterId());
					session.setAttribute("content", content);
					session.setAttribute("title", title);
					session.setAttribute("type", replyType);
					
					RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/replyLetter.jsp");
					rd.forward(request, response);
					return;
				}catch(ArrayIndexOutOfBoundsException e) {
						System.out.println("無惡魔信可回");
						RequestDispatcher rd = request.getRequestDispatcher("/_07_letter/driftLetter.jsp");
						rd.forward(request, response);
						return;
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			
			}
			
	}

}
