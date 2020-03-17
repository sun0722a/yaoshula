package _05_product.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.CascadeType;
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
	private Integer productId;
	private String productName;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CategoryBean_Category")
	private CategoryBean category;
	private Integer price;
	private String formate;
	private String fileName;
	private Blob image;
	private Clob detail;
	private Integer stock;

	public ProductBean(Integer productId, String productName, Integer price, String formate, String fileName,
			Blob image, Clob detail, Integer stock) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.formate = formate;
		this.fileName = fileName;
		this.image = image;
		this.detail = detail;
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

	public String getFormate() {
		return formate;
	}

	public void setFormate(String formate) {
		this.formate = formate;
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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
