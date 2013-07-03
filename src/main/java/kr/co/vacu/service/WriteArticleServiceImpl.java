package kr.co.vacu.service;

import kr.co.vacu.dao.ArticleDao;
import kr.co.vacu.dao.IdGenerator;
import kr.co.vacu.domain.Article;

public class WriteArticleServiceImpl {
	private ArticleDao articleDao;
	private IdGenerator idGenerator;

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}
	
	public Article writeArticle(Article article) {
		
        String id = idGenerator.getNextId();
        article.setId(id);
        articleDao.insert(article);
        return article;		
	}
}
