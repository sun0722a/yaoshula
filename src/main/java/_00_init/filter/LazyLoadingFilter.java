package _00_init.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.HibernateUtils;

<<<<<<< HEAD
@WebFilter(urlPatterns = { "/product/ShowProductInfo", "/init/getProductImage", "/init/getProductDetail", "/order/showHistoryOrder", "/order/checkOrder" })
=======
@WebFilter(urlPatterns = { "/init/getProductImage", "/init/getProductDetail", "/product/ShowProductInfo",
		"/order/shoppingCart", "/order/showHistoryOrder" })
>>>>>>> 7a4065b70d0f7738c36cb5c4c6c705564032ba5f
public class LazyLoadingFilter implements Filter {

	SessionFactory factory;

	public LazyLoadingFilter() {
	}

	public void destroy() {
		factory.close();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			chain.doFilter(request, response);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException("LazyLoadingFilter捕捉例外: " + e.getMessage());
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		factory = HibernateUtils.getSessionFactory();
	}

}
