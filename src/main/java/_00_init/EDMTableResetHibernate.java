package _00_init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.GlobalService;
import _00_init.util.HibernateUtils;
import _05_product.model.CategoryBean;
import _05_product.model.ProductBean;

/* 未完成: data未製作 */

public class EDMTableResetHibernate {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		String line = "";
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		// 1. ProductCategory
		try {
			tx = session.beginTransaction();
			try (FileReader fr = new FileReader("data/ProductCategory.dat");
					BufferedReader br = new BufferedReader(fr);) {
				while ((line = br.readLine()) != null) {
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					String categoryTitle = token[0];
					String categoryName = token[1];
					CategoryBean cb = new CategoryBean(null, categoryTitle, categoryName);
					session.save(cb);
					session.flush();
				}
				tx.commit();
				System.out.println("ProductCategory表格新增成功");
			} catch (IOException e) {
				System.err.println("新建ProductCategory表格時發生IO例外: " + e.getMessage());
			}
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
		}

		// 2. Products
		session = factory.getCurrentSession();
		tx = null;
		try {
			tx = session.beginTransaction();
			File file = new File("data/Products.dat");
			try (FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");

					String productName = token[0];
					/* data example: 天使:書籍 */
					String categoryTitle = token[1].split(":")[0];
					String categoryName = token[1].split(":")[1];
					CategoryBean cb = null;
					String hql = "FROM CategoryBean cb WHERE (cb.categoryTitle like :categoryTitle) "
							+ "&& (cb.categoryName like :categoryName)";
					@SuppressWarnings("unchecked")
					List<CategoryBean> beans = session.createQuery(hql).setParameter("categoryTitle", categoryTitle)
							.setParameter("categoryName", categoryName).getResultList();
					if (beans.size() > 0) {
						cb = beans.get(0);
					}
					Integer price = Integer.parseInt(token[2].trim());
					String formateTitle1 = token[3];
					String formateContent1 = token[4];
					String formateTitle2 = token[5];
					String formateContent2 = token[6];
					String fileName = GlobalService.extractFileName(token[7].trim());
					Blob image = GlobalService.fileToBlob(token[8].trim());
					Clob detail = GlobalService.fileToClob(token[9]);
					Integer sales = Integer.parseInt(token[10].trim());
					Integer stock = Integer.parseInt(token[11].trim());
					ProductBean product = new ProductBean(null, productName, cb, price, formateTitle1, formateContent1,
							formateTitle2, formateContent2, fileName, image, detail, sales, stock);

					session.save(product);
					session.flush();
				}
				tx.commit();
				System.out.println("Products表格新增成功");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			System.err.println("新建Products表格時發生IO例外: " + ex.getMessage());
		}
	}

}