package _01_register.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _01_register.service.MemberService;

@WebServlet("/register/checkEmail")
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Writer os = null;
		os = response.getWriter();

		String email = request.getParameter("email");
//		MemberService service = new MemberServiceImpl();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberService service = ctx.getBean(MemberService.class);
		if (email.trim().length() != 0) {
			boolean exist = service.emailExists(email);
			if (!exist) {
				os.write("此信箱可使用");
			} else {
				os.write("此信箱已被使用");
			}
		}
		return;
	}
}