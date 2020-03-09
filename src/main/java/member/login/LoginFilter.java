package member.login;

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

import _03_personPage.model.MemberBean;


@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "url_1", value = "/login/*")
		})
public class LoginFilter implements Filter {

	List<String> url = new ArrayList<String>();
	String servletPath;
	String contextPath;
	String requestURI;
	
    public LoginFilter() {
    	

    }
    
	public void init(FilterConfig fConfig) throws ServletException {
			Enumeration<String> e = fConfig.getInitParameterNames();
			while(e.hasMoreElements()) {
				String path = e.nextElement();
				url.add(fConfig.getInitParameter(path));
			}
		}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean isRequestedSessionIdValid = false;
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			servletPath = req.getServletPath();
			contextPath = req.getContextPath();
			requestURI =req.getRequestURI();
			isRequestedSessionIdValid = req.isRequestedSessionIdValid();
			
			if(mustLogin()) {
				if(checkLogin(req)) {
					
					//需要登入 但已經登入
					chain.doFilter(request, response);
				}else {   //
					HttpSession session = req.getSession();
					
					if(! isRequestedSessionIdValid) {
						session.setAttribute("timeOut", "使用逾時,請重新登入");
					}else {
						
						//記住原本的REQUESTuri 如果登入成功 系統可以自動轉入
						session.setAttribute("requestURI", requestURI);
					}
					
					resp.sendRedirect(contextPath + "/_01_login/login.jsp");
					return;
				}
			}else {  //如果不用登入 就直接交棒給要執行的程式
				chain.doFilter(request, response);
			}
			
			
		}else {
			throw new ServletException("Request/Response 型態錯誤(極不可能發生)");
		}
		
	}

	
	
	private boolean checkLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		MemberBean loginToken = (MemberBean) session.getAttribute("LoginOK");
		if(loginToken == null) {
			return false;
		}else {
			return true;
		}
	}

	private boolean mustLogin() {
		boolean login = false;
		for(String sURL : url) {
			if(sURL.endsWith("*")) {
				sURL = sURL.substring(0,sURL.length() - 1);
				if(servletPath.startsWith(sURL)) {
					login = true;
					break;
				}
			}else {
				if(servletPath.equals(sURL)) {
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
