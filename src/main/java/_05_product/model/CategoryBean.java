package _05_product.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

// 本類別封裝單筆書籍資料
@Entity
@Table(name = "ProductCategorys")
public class CategoryBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	private String categoryTitle;
	private String categoryName;
	@OneToMany(mappedBy = "category")
	private Set<ProductBean> products = new LinkedHashSet<>();

	public CategoryBean(Integer categoryId, String categoryTitle, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryName = categoryName;
	}

	public CategoryBean() {
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<ProductBean> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductBean> products) {
		this.products = products;
	}

}
