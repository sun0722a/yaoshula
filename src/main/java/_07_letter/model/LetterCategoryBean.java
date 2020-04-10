package _07_letter.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "letterCategory")
public class LetterCategoryBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "native")
	private Integer letterCategoryId;
	private String categoryName;
	@OneToMany(mappedBy = "categoryName", cascade = CascadeType.ALL)
	private Set<LetterBean> letters = new LinkedHashSet<>();
	
	
	
	
	public LetterCategoryBean(Integer letterCategoryId, String categoryName, Set<LetterBean> letters) {
		super();
		this.letterCategoryId = letterCategoryId;
		this.categoryName = categoryName;
		this.letters = letters;
	}
	public Integer getLetterCategoryId() {
		return letterCategoryId;
	}
	public void setLetterCategoryId(Integer letterCategoryId) {
		this.letterCategoryId = letterCategoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Set<LetterBean> getLetters() {
		return letters;
	}
	public void setLetters(Set<LetterBean> letters) {
		this.letters = letters;
	}
	
	
}
