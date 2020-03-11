package _03_personPage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_personPage.dao.MemberDao;
import _03_personPage.dao.impl.MemberDaoImpl_Jdbc;
import _03_personPage.model.MemberBean;

//測試用Servlet，session應該從登入取得

@WebServlet("/personPage")
public class personPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDao memberService = (MemberDao) new MemberDaoImpl_Jdbc();
		MemberBean mb = memberService.queryMember(1);
		session.setAttribute("LoginOK", mb);
		RequestDispatcher rd = request.getRequestDispatcher("/_03_personPage/personPage.jsp");
		rd.forward(request, response);
	}
}
