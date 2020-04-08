package _06_article.model;

import java.util.LinkedHashSet;
import java.util.Set;

import _06_article.service.ArticleService;

public class Articles {

	Set<String> angleCategorySet = new LinkedHashSet();
	Set<String> evilCategorySet = new LinkedHashSet();
	ArticleService service;

	public Articles() {
	}

	public Set<String> getAngleCategorySet() {
		return angleCategorySet;
	}

	public Set<String> getEvilCategorySet() {
		return evilCategorySet;
	}
}
