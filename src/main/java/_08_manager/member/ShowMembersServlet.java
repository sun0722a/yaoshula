package _08_manager.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

// 查詢帳號
@WebServlet("/manager/showMembers")
public class ShowMembersServlet extends HttpServlet {
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

		// 必須是空字串
		String searchStr = request.getParameter("searchStr") == null ? "" : request.getParameter("searchStr");

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberService memberService = ctx.getBean(MemberService.class);
		Map<MemberBean, Integer> membersTime = memberService.getMembers(searchStr);
		Map<MemberBean, Integer> membersNum = new LinkedHashMap<>();
		List<Entry<MemberBean, Integer>> list = new ArrayList<Map.Entry<MemberBean, Integer>>(membersTime.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<MemberBean, Integer>>() {
			public int compare(Map.Entry<MemberBean, Integer> o1, Map.Entry<MemberBean, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
		for (Map.Entry<MemberBean, Integer> t : list) {
			membersNum.put(t.getKey(), t.getValue());
		}

		request.setAttribute("searchStr", searchStr);
		request.setAttribute("member_map", membersNum);

		RequestDispatcher rd = request.getRequestDispatcher("/_08_manager/member/allMembers.jsp");
		rd.forward(request, response);
		return;
	}

}
