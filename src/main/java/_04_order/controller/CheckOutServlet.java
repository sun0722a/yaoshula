package _04_order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;


@WebServlet("/order/checkOrder")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		
			String requestURI = request.getRequestURI();
			
			if(session == null || session.isNew()){
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/_02_login/login.jsp"));
				return;
			}
			
			session.setAttribute("requestURI", requestURI);
			
//			MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
//			if(mb == null) {
//				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_02_login/login.jsp"));
//			}
			
			String[] content1 = request.getParameterValues("content");
			
			System.out.println(content1);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/checkOrder.jsp");
			rd.forward(request, response);
			return;
		}
		
	}


