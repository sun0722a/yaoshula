package _08_manager.product;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _00_init.util.GlobalService;
import _05_product.model.ProductBean;
import _05_product.model.ProductFormatBean;
import _05_product.service.ProductService;

// 新增or修改商品
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/manager/addProduct")
public class AddProductServlet extends HttpServlet {
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

		String idStr = request.getParameter("productId");
		Integer productId = 0;
		try {
			productId = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			;
		}
		String productName = "";
		Integer categoryId = 0;
		Integer price = 0;
		String fileName = "";
		String detail = "";
		String formatTitle1 = "";
		Set<String> formatContents1 = new LinkedHashSet<String>();
		for (String formatContent : request.getParameterValues("formatContent1")) {
			formatContents1.add(formatContent.trim());
		}
		String formatTitle2 = "";
		Set<String> formatContents2 = new LinkedHashSet<String>();
		for (String formatContent : request.getParameterValues("formatContent2")) {
			formatContents2.add(formatContent.trim());
		}
		List<Integer> stocks = new ArrayList<>();
		for (String stockStr : request.getParameterValues("stock")) {
			stocks.add(Integer.parseInt(stockStr.trim()));
		}
		long sizeInBytes = 0;
		InputStream is = null;

		Collection<Part> parts = request.getParts();
		if (parts != null) {
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				// 逐項讀取使用者輸入資料
				if (p.getContentType() == null) {
					if (fldName.equals("productName")) {
						productName = value;
					} else if (fldName.equals("categoryId")) {
						categoryId = Integer.parseInt(value.trim());
					} else if (fldName.equals("price")) {
						price = Integer.parseInt(value.trim());
					} else if (fldName.equals("detail")) {
						detail = value;
					} else if (fldName.equals("formatTitle1")) {
						formatTitle1 = value;
					} else if (fldName.equals("formatTitle2")) {
						formatTitle2 = value;
					}
				} else {
					// 取出圖片檔的檔名
					fileName = p.getSubmittedFileName();
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (fileName != null && fileName.trim().length() > 0) {
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					}
				}
			}
		} else {
			System.out.println("此表單不是上傳檔案的表單(RegisterServlet)");
		}

		try {
			Blob blob = null;
			if (is != null) {
				blob = GlobalService.fileToBlob(is, sizeInBytes);
			}
			Clob clob = GlobalService.stringToClob(detail);

			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			ProductService productService = ctx.getBean(ProductService.class);

			if (productId != 0) {
				// 修改商品
				ProductBean pb = productService.getProduct(productId);
				if (pb != null) {
					pb.setProductName(productName);
					pb.setCategory(productService.getCategory(categoryId));
					pb.setPrice(price);
					if (blob != null) {
						pb.setFileName(fileName);
						pb.setImage(blob);
					}
					pb.setDetail(clob);
					// 刪除原本規格，新增新的規格
					productService.deleteProductFormat(pb);
					int count = 0;
					Set<ProductFormatBean> productFormats = new LinkedHashSet<>();
					for (String formatContent1 : formatContents1) {
						for (String formatContent2 : formatContents2) {
							ProductFormatBean pfb = new ProductFormatBean(null, formatTitle1, formatContent1,
									formatTitle2, formatContent2, stocks.get(count), pb);
							productFormats.add(pfb);
							count++;
						}
					}
					pb.setProductFormat(productFormats);

					productService.insertProduct(pb);
				}
			} else {
				// 新增商品
				ProductBean pb = new ProductBean(null, productName, productService.getCategory(categoryId), price,
						fileName, blob, clob, 0, null);
				Set<ProductFormatBean> productFormats = new LinkedHashSet<>();
				int count = 0;
				for (String formatContent1 : formatContents1) {
					for (String formatContent2 : formatContents2) {
						ProductFormatBean pfb = new ProductFormatBean(null, formatTitle1, formatContent1, formatTitle2,
								formatContent2, stocks.get(count), pb);
						productFormats.add(pfb);
						count++;
					}
				}
				pb.setProductFormat(productFormats);

				productService.insertProduct(pb);
			}

			response.sendRedirect(getServletContext().getContextPath() + "/manager/showProducts");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/manager/showProducts");
			rd.forward(request, response);
			return;
		}
	}
}
