package _05_product.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

// 本類別封裝單筆書籍資料
@Entity
@Table(name = "Products")
public class ProductBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer productId;
	private String productName;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CategoryBean_Category")
	private CategoryBean category;
	private Integer price;
	@Id
	private String formateTitle1;
	@Id
	private String formateContent1;
	@Id
	private String formateTitle2;
	@Id
	private String formateContent2;
	private String fileName;
	private Blob image;
	private Clob detail;
	private Integer sales;
	private Integer stock;

	public ProductBean(Integer productId, String productName, CategoryBean category, Integer price,
			String formateTitle1, String formateContent1, String formateTitle2, String formateContent2, String fileName,
			Blob image, Clob detail, Integer sales, Integer stock) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.formateTitle1 = formateTitle1;
		this.formateContent1 = formateContent1;
		this.formateTitle2 = formateTitle2;
		this.formateContent2 = formateContent2;
		this.fileName = fileName;
		this.image = image;
		this.detail = detail;
		this.sales = sales;
		this.stock = stock;
	}

	public ProductBean() {
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public CategoryBean getCategory() {
		return category;
	}

	public void setCategory(CategoryBean category) {
		this.category = category;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getFormateTitle1() {
		return formateTitle1;
	}

	public void setFormateTitle1(String formateTitle1) {
		this.formateTitle1 = formateTitle1;
	}

	public String getFormateContent1() {
		return formateContent1;
	}

	public void setFormateContent1(String formateContent1) {
		this.formateContent1 = formateContent1;
	}

	public String getFormateTitle2() {
		return formateTitle2;
	}

	public void setFormateTitle2(String formateTitle2) {
		this.formateTitle2 = formateTitle2;
	}

	public String getFormateContent2() {
		return formateContent2;
	}

	public void setFormateContent2(String formateContent2) {
		this.formateContent2 = formateContent2;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Clob getDetail() {
		return detail;
	}

	public void setDetail(Clob detail) {
		this.detail = detail;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
