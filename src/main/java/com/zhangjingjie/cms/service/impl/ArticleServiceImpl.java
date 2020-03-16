package com.zhangjingjie.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangjingjie.cms.dao.ArticleMapper;
import com.zhangjingjie.cms.domain.Article;
import com.zhangjingjie.cms.service.ArticleService;
@ContextConfiguration
@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleMapper mapper;
	
	@Override
	public int insert(Article article) {
		return mapper.insert(article);
	}

	@Override
	public PageInfo<Article> selects(Article article, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> list = mapper.selects(article);
		return new PageInfo<Article>(list);
	}

	@Override
	public Article select(Integer id) {
		return mapper.select(id);
	}

	@Override
	public int update(Article article) {
		return mapper.update(article);
	}

}
