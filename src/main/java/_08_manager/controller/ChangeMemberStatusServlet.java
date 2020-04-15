package _08_manager.controller;

import java.io.IOException;
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

// 封鎖帳號or解除封鎖
@WebServlet("/ChangeMemberStatusServlet")
public class ChangeMemberStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 使用逾時，回首頁
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		String memberLock = request.getParameter("memberLock");
		String idStr = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			;
		}

		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		MemberService memberService = ctx.getBean(MemberService.class);
		MemberBean mb = memberService.getMember(id);

		if (memberLock.equals("封鎖帳號")) {
			mb.setStatus("封鎖");
			memberService.saveMember(mb);
		} else if (memberLock.equals("解除封鎖")) {
			mb.setStatus("正常");
			memberService.saveMember(mb);
		}

		response.sendRedirect(getServletContext().getContextPath() + "/manager/showMemberInfo?id=" + id);
		return;
	}

}
