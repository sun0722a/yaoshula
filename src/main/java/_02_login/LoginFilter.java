package _02_login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;

@WebFilter(urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = "url_1", value = "/_03_personPage/personPage.jsp"),
		@WebInitParam(name = "url_2", value = "/order/*"),
		@WebInitParam(name = "url_3", value = "/_06_article/addArticle.jsp"),
		@WebInitParam(name = "url_4", value = "/article/AddComment"),
		@WebInitParam(name = "url_5", value = "/article/LikeArticle"),
		@WebInitParam(name = "url_6", value = "/article/Report"),
		@WebInitParam(name = "url_7", value = "/_07_letter/letterInfo.jsp"),
		@WebInitParam(name = "url_8", value = "/_07_letter/*")})
public class LoginFilter implements Filter {

	List<String> url = new ArrayList<String>();
	String contextPath;
	String queryString;

	public LoginFilter() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
		Enumeration<String> e = fConfig.getInitParameterNames();
		while (e.hasMoreElements()) {
			String name = e.nextElement();
			String value = fConfig.getInitParameter(name);
			url.add(value);
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			String servletPath = req.getServletPath();
			contextPath = req.getContextPath();
			if (mustLogin(servletPath)) {
				if (checkLogin(req)) {
					// 需要登入 但已經登入
					chain.doFilter(request, response);
				} else {
					HttpSession session = req.getSession();
					session.setAttribute("target", servletPath);
					resp.sendRedirect(contextPath + "/_02_login/login.jsp");
					return;
				}
			} else { // 如果不用登入 就直接交棒給要執行的程式
				chain.doFilter(request, response);
			}

		} else {
			throw new ServletException("Request/Response 型態錯誤(極不可能發生)");
		}

	}

	private boolean checkLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberBean loginToken = (MemberBean) session.getAttribute("LoginOK");
		if (loginToken == null) {
			return false;
		} else {
			return true;
		}
	}

	private boolean mustLogin(String servletPath) {
		boolean login = false;
		for (String sURL : url) {
			if (sURL.endsWith("*")) {
				sURL = sURL.substring(0, sURL.length() - 1);
				if (servletPath.startsWith(sURL)) {
					login = true;
					break;
				}
			} else {
				if (servletPath.equals(sURL)) {
					login = true;
					break;
				}
			}
		}
		return login;
	}

	@Override
	public void destroy() {
	}
}
