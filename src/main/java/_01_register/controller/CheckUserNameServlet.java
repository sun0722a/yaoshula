package _01_register.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _01_register.service.MemberService;
import _01_register.service.impl.MemberServiceImpl;

@WebServlet("/register/checkUserName")
public class CheckUserNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Writer os = null;
		os = response.getWriter();

		String userName = request.getParameter("userName");
		MemberService service = new MemberServiceImpl();
		boolean exist = service.idExists(userName);
		if (exist) {
			os.write("此帳號可使用");
		} else {
			os.write("此帳號已被使用");
		}

	}
}