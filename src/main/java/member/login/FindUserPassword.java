package member.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebFilter("/login/login.jsp")
public class FindUserPassword implements Filter{
	String requestURI;
	public FindUserPassword() {
		
	}
	
	@Override   //在畫面還沒產生的時候 先執行過濾器 去找有沒有cookie傳來的帳號及密碼
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			
			String cookieName = "";
			String user = "";
			String password = "";
			String rememberMe = "";
			
			Cookie[] cookies = req.getCookies();  //讀取cookie
			if(cookies != null) {
				for(int i = 0;i<cookies.length;i++) {
					cookieName = cookies[i].getName();
					
					if(cookieName.equals("user")) {
						user = cookies[i].getValue();
					}else if(cookieName.equals("password")) {
//						String tmp = cookies[i].getValue();
						 password = cookies[i].getValue();
						
//						if(tmp != null) {
//							password = GlobalService.decryptString(GlobalService.KEY, tmp); 
//							password = MemberServiceImpl
//							
//						}
					}
					else if(cookieName.equals("rememberMe")) {
						rememberMe = cookies[i].getValue();
					}
				}
				
			}else {
				;
			}
			
			request.setAttribute("rememberMe",rememberMe);
			request.setAttribute("user", user);
			request.setAttribute("password", password);
		}
		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

	

	@Override
	public void destroy() {
		
		
	}
	
}
