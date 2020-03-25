package _00_init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.GlobalService;
import _00_init.util.HibernateUtils;
import _05_product.model.CategoryBean;
import _05_product.model.ProductBean;
import _05_product.model.ProductFormatBean;

/* 未完成: data未製作完成(only 測試版) */

public class EDMTableResetHibernate {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		String line = "";
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		// 商品類(product)一起新增
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		// 1. ProductCategory
		try (FileReader fr1 = new FileReader("data/product/productCategory.dat");
				BufferedReader br1 = new BufferedReader(fr1);) {
			while ((line = br1.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token1 = line.split("\\|");
				String categoryTitle1 = token1[0];
				String categoryName1 = token1[1];
				String[] categoryProducts = token1[2].split(",");
				CategoryBean categoryBean = new CategoryBean(null, categoryTitle1, categoryName1, null);

				// 2. Products
				Set<ProductBean> products = new LinkedHashSet<>();
				try (FileReader fr2 = new FileReader("data/product/products.dat");
						BufferedReader br2 = new BufferedReader(fr2);) {
					while ((line = br2.readLine()) != null) {
						if (line.startsWith(UTF8_BOM)) {
							line = line.substring(1);
						}
						String[] token2 = line.split("\\|");
						String productName = token2[0];

						for (String str : categoryProducts) {
							if (str.equals(productName)) {
								CategoryBean cb = categoryBean;
								Integer price = Integer.parseInt(token2[1].trim());
								String fileName = GlobalService.extractFileName(token2[2].trim());
								Blob image = GlobalService.fileToBlob(token2[2].trim());
								Clob detail = GlobalService.fileToClob(token2[3]);

								ProductBean product = new ProductBean(null, productName, cb, price, fileName, image,
										detail, 0, null);

								// 3. ProductFormat
								Set<ProductFormatBean> productFormats = new LinkedHashSet<>();
								try (FileReader fr3 = new FileReader("data/product/productFormat.dat");
										BufferedReader br3 = new BufferedReader(fr3);) {
									while ((line = br3.readLine()) != null) {
										if (line.startsWith(UTF8_BOM)) {
											line = line.substring(1);
										}
										String[] token = line.split("\\|");
										String productNameCheck = token[5];
										if (productName.equals(productNameCheck)) {
											String formatTitle1 = token[0];
											String formatContent1 = token[1];
											String formatTitle2 = token[2];
											String formatContent2 = token[3];
											Integer stock = Integer.parseInt(token[4].trim());
											
											ProductFormatBean productFormat = new ProductFormatBean(null, formatTitle1,
													formatContent1, formatTitle2, formatContent2, stock, product);
											productFormats.add(productFormat);
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
									if (tx != null) {
										tx.rollback();
									}
									System.err.println("新建ProductFormat表格時發生例外: " + e.getMessage());
								}
								product.setProductFormat(productFormats);
								products.add(product);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (tx != null) {
						tx.rollback();
					}
					System.err.println("新建Products表格時發生例外: " + e.getMessage());
				}
				categoryBean.setProducts(products);
				session.save(categoryBean);
				session.flush();
				System.out.println("ProductCategory表格新增成功");
			}
			tx.commit();
		} catch (IOException e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println("新建ProductCategory表格時發生IO例外: " + e.getMessage());
		}

	}

}