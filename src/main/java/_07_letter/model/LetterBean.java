package _07_letter.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import _06_article.model.ArticleCategoryBean;


@Entity
@Table(name="Letters")
public class LetterBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "native")
	private Integer letterNo;
	private String 	letterName;
	private String 	letterAuthor;
	private Timestamp sendTime;
	private Clob letterContent;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_LetterCategory_CategoryId")
	private ArticleCategoryBean categoryName;
	private String status;
	
	
	
	public LetterBean(Integer letterNo, String letterName, String letterAuthor, Timestamp sendTime, Clob letterContent,
			ArticleCategoryBean categoryName, String status) {
		super();
		this.letterNo = letterNo;
		this.letterName = letterName;
		this.letterAuthor = letterAuthor;
		this.sendTime = sendTime;
		this.letterContent = letterContent;
		this.categoryName = categoryName;
		this.status = status;
	}
	public Integer getLetterNo() {
		return letterNo;
	}
	public void setLetterNo(Integer letterNo) {
		this.letterNo = letterNo;
	}
	public String getLetterName() {
		return letterName;
	}
	public void setLetterName(String letterName) {
		this.letterName = letterName;
	}
	public String getLetterAuthor() {
		return letterAuthor;
	}
	public void setLetterAuthor(String letterAuthor) {
		this.letterAuthor = letterAuthor;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public Clob getLetterContent() {
		return letterContent;
	}
	public void setLetterContent(Clob letterContent) {
		this.letterContent = letterContent;
	}
	public ArticleCategoryBean getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(ArticleCategoryBean categoryName) {
		this.categoryName = categoryName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
