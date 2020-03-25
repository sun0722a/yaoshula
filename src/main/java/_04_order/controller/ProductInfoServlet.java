package _04_order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;
import _05_product.model.ProductBean;
import _05_product.service.ProductService;
import _05_product.service.impl.ProductServiceImpl;

@WebServlet("/product/ShowProductInfo")
public class ProductInfoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	int memberId = 0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
		
			HttpSession session = request.getSession();
			MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
			if(mb == null) {
				memberId = 0;
			}else {
				memberId = mb.getId();
			}
			
		//從jsp取得所點取的productID為何
			String productIdStr = request.getParameter("productId");
			Integer productId = Integer.parseInt(productIdStr);
			
			ProductService service = new ProductServiceImpl();
			//利用getProduct取得該ID所擁有的資訊
			
			List<ProductBean> bean = service.getProductInfo(productId);
			
			
			//把bean暫存到請求物件內
			request.setAttribute("productInfo",bean);
			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/productInfo.jsp");
			rd.forward(request, response);
			return;
		
	}
	

	
	
}
