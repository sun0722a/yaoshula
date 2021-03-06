﻿package _06_article.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
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

import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "Articles")
public class ArticleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleId;
	private String title;
	private Integer authorId;
	private String authorName;
	private Timestamp publishTime;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_ArticleCategoryBean_Category")
	private ArticleCategoryBean category;
	private Clob content;
	private String fileName;
	private Blob image;
	private Integer likes;
	private String status;

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	@OrderBy(clause = "publishTime")
	Set<CommentBean> articleComments = new LinkedHashSet<>();

	public ArticleBean(Integer articleId, String title, Integer authorId, String authorName, Timestamp publishTime,
			ArticleCategoryBean category, Clob content, String fileName, Blob image, Integer likes, String status,
			Set<CommentBean> articleComments) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.authorId = authorId;
		this.authorName = authorName;
		this.publishTime = publishTime;
		this.category = category;
		this.content = content;
		this.fileName = fileName;
		this.image = image;
		this.likes = likes;
		this.status = status;
		this.articleComments = articleComments;
	}

	public ArticleBean() {
		super();
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public ArticleCategoryBean getCategory() {
		return category;
	}

	public void setCategory(ArticleCategoryBean category) {
		this.category = category;
	}

	public Clob getContent() {
		return content;
	}

	public void setContent(Clob content) {
		this.content = content;
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

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<CommentBean> getArticleComments() {
		return articleComments;
	}

	public void setArticleComments(Set<CommentBean> articleComments) {
		this.articleComments = articleComments;
	}

}
