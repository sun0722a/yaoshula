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

import _07_letter.Service.LetterService;
import _07_letter.model.LetterBean;

@WebServlet("/reply")
public class ReplyServlet extends HttpServlet {
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

		String type = request.getParameter("type");
		System.out.println(type);

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		LetterService letterService = ctx.getBean(LetterService.class);

		LetterBean lb = null;
		//如果選擇的是天使主題
		if (type.equals("angel")) {
			Map<Integer, LetterBean> letterMap = letterService.getUnfinishedLetter("天使", "n");

			Integer mapSize = letterMap.size();
			Set<Integer> letterNo = letterMap.keySet();

			// 隨機取得信件編號的index值
			try {
				int randomNo = (int) (Math.random() * mapSize);
				Integer letterId = (Integer) letterNo.toArray()[randomNo];

				System.out.println("隨機index值:" + randomNo + "隨機數的key:" + letterId);
				lb = letterService.getLetter(letterId);
				session.setAttribute("letter", lb);
				
				
				System.out.println("內容" + lb.getLetterContent());
				System.out.println("title:" + lb.getLetterTitle());
				
				response.sendRedirect(response.encodeRedirectURL("/_07_letter/replyAngel.jsp"));

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
			//如果選擇的是惡魔主題	
			
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
				session.setAttribute("lb", lb);
				
				System.out.println("回覆類型: " + type);
				System.out.println("內容" + lb.getLetterContent());
				System.out.println("title:" + lb.getLetterTitle());
				
//				response.sendRedirect(response.encodeRedirectURL(getServletContext().getContextPath() +  "/_07_letter/replyDevil.jsp"));
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
		}

	}
}
