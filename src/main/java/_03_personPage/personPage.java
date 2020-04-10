package _03_personPage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.dao.MemberDao;
import _01_register.dao.impl.MemberDaoImpl_Hibernate;
import _01_register.model.MemberBean;

//測試用Servlet，session應該從登入取得

@WebServlet("/personPage/test")
public class personPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDao memberService = (MemberDao) new MemberDaoImpl_Hibernate();
		MemberBean mb = memberService.getMember(1);
		session.setAttribute("LoginOK", mb);
		RequestDispatcher rd = request.getRequestDispatcher("/_03_personPage/personPage.jsp");
		rd.forward(request, response);
	}
	
}