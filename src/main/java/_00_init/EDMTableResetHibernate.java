package _00_init;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00_init.util.GlobalService;
import _00_init.util.HibernateUtils;
import _04_order.model.OrderBean;
import _04_order.model.OrderItemBean;
import _05_product.model.CategoryBean;
import _05_product.model.ProductBean;
import _05_product.model.ProductFormatBean;
import _06_article.model.ArticleBean;
import _06_article.model.ArticleCategoryBean;
import _06_article.model.CommentBean;

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

		// 訂單類(order)一起新增
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		// 1. Orders
		try (FileReader fr1 = new FileReader("data/order/orders.dat"); BufferedReader br1 = new BufferedReader(fr1);) {
			while ((line = br1.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token1 = line.split("\\|");
				Integer memberId = Integer.parseInt(token1[0]);
				String memberName = token1[1];
//				Integer totalPrice = Integer.parseInt(token1[2]);
				Integer totalPrice = 0;
				String address = token1[2];
				String phoneNumber = token1[3];
				String orderNote = token1[4];
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = simpleDateFormat.parse(token1[5].trim());
				Date orderDate = new Date(date.getTime());
				date = simpleDateFormat.parse(token1[6].trim());
				Date shippingDate = new Date(date.getTime());
				date = simpleDateFormat.parse(token1[7].trim());
				Date arriveDate = new Date(date.getTime());

				OrderBean orderBean = new OrderBean(null, memberId, memberName, totalPrice, address, phoneNumber,
						orderNote, orderDate, shippingDate, arriveDate, "待出貨", null);

				// 2. OrderItems
				Set<OrderItemBean> orderItems = new LinkedHashSet<>();
				OrderItemBean orderItemBean = null;
				try (FileReader fr2 = new FileReader("data/order/orderItems.dat");
						BufferedReader br2 = new BufferedReader(fr2);) {
					while ((line = br2.readLine()) != null) {
						if (line.startsWith(UTF8_BOM)) {
							line = line.substring(1);
						}
						String[] token2 = line.split("\\|");
						String orderNoCheck = token2[6];
						if (token1[8].equals(orderNoCheck)) {
							Integer productId = Integer.parseInt(token2[0].trim());
							String productName = token2[1];
							String formatContent1 = token2[2];
							String formatContent2 = token2[3];
							Integer unitPrice = Integer.parseInt(token2[4].trim());
							Integer quantity = Integer.parseInt(token2[5].trim());
							totalPrice += unitPrice * quantity;
							orderItemBean = new OrderItemBean(null, productId, productName, formatContent1,
									formatContent2, unitPrice, quantity, orderBean);
							orderItems.add(orderItemBean);
						}
					}

				} catch (Exception e) {
					if (tx != null) {
						tx.rollback();
					}
					System.err.println("新建OrderItems表格時發生IO例外: " + e.getMessage());
					e.printStackTrace();
				}
				orderBean.setTotalPrice(totalPrice);
				orderBean.setOrderItems(orderItems);
				session.save(orderBean);
				session.flush();
				System.out.println("Orders表格新增成功");
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println("新建Orders表格時發生IO例外: " + e.getMessage());
		}

		// 文章類(article)一起新增
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		// 1. ArticleCategory
		try (FileReader fr1 = new FileReader("data/article/articleCategory.dat");
				BufferedReader br1 = new BufferedReader(fr1);) {
			while ((line = br1.readLine()) != null) {
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token1 = line.split("\\|");
				String categoryTitle = token1[0];
				String categoryName = token1[1];
				ArticleCategoryBean articleCategoryBean = new ArticleCategoryBean(null, categoryTitle, categoryName,
						null);

				// 2. Articles
				Set<ArticleBean> articles = new LinkedHashSet<>();
				try (FileReader fr2 = new FileReader("data/article/articles.dat");
						BufferedReader br2 = new BufferedReader(fr2);) {
					while ((line = br2.readLine()) != null) {
						if (line.startsWith(UTF8_BOM)) {
							line = line.substring(1);
						}
						String[] token2 = line.split("\\|");
						String articleCategoryCkeck = token2[8];

						if (token1[2].equals(articleCategoryCkeck)) {
							String title = token2[1];
							Integer authorId = Integer.parseInt(token2[2].trim());
							String authorName = token2[3];
							Timestamp publishTime = Timestamp.valueOf(token2[4].trim());
							ArticleCategoryBean acb = articleCategoryBean;
							Clob content = GlobalService.fileToClob(token2[5]);
							String fileName = GlobalService.extractFileName(token2[6].trim());
							Blob image = GlobalService.fileToBlob(token2[6].trim());
							Integer likes = Integer.parseInt(token2[7].trim());
							
							ArticleBean articleBean = new ArticleBean(null, title, authorId, authorName, publishTime,
									acb, content, fileName, image, likes, "正常", null);
							
							// 3. Comments
							Set<CommentBean> comments = new LinkedHashSet<>();
							try (FileReader fr3 = new FileReader("data/article/comments.dat");
									BufferedReader br3 = new BufferedReader(fr3);) {
								while ((line = br3.readLine()) != null) {
									if (line.startsWith(UTF8_BOM)) {
										line = line.substring(1);
									}
									String[] token3 = line.split("\\|");
									String articleNoCheck = token3[5];
									if (token2[0].equals(articleNoCheck)) {
										Integer commentAuthorId = Integer.parseInt(token3[0].trim());
										String commentAuthorName = token3[1];
										Timestamp commentPublishTime = Timestamp.valueOf(token3[2].trim());
										String commentContent = token3[3];
										ArticleBean ab = articleBean;
										Integer commentLikes = Integer.parseInt(token3[4].trim());

										CommentBean commentBean = new CommentBean(null, commentAuthorId,
												commentAuthorName, commentPublishTime, commentContent, ab, commentLikes,"正常");
										comments.add(commentBean);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								if (tx != null) {
									tx.rollback();
								}
								System.err.println("新建ProductFormat表格時發生例外: " + e.getMessage());
							}
							articleBean.setArticleComments(comments);
							articles.add(articleBean);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (tx != null) {
						tx.rollback();
					}
					System.err.println("新建Products表格時發生例外: " + e.getMessage());
				}
				articleCategoryBean.setArticles(articles);
				session.save(articleCategoryBean);
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