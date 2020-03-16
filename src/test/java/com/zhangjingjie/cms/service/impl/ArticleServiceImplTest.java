package com.zhangjingjie.cms.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.zhangjingjie.cms.domain.Article;
import com.zhangjingjie.cms.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class ArticleServiceImplTest {
	
	@Autowired
	private ArticleService articleservice;

	@Test
	public void testInsert() {
		Article article = new Article();
		article.setTitle("test");
		article.setContent("11111111");
		article.setUserId(1);
		article.setChannelId(1);
		article.setCategoryId(1);
		article.setCreated(new Date());
		
		articleservice.insert(article);
	}

	@Test
	public void testSelects() {
		Article article = new Article();
		PageInfo<Article> selects = articleservice.selects(article, 1, 1);
		System.out.println(selects);
	}

}
