package com.zhangjingjie.cms.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangjingjie.cms.dao.CommentMapper;
import com.zhangjingjie.cms.domain.Article;
import com.zhangjingjie.cms.domain.Comment;
import com.zhangjingjie.cms.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper mapper;
	@Override
	public int insert(Comment comment) {
		try {
			mapper.insert(comment);
			mapper.updateArticle(comment.getArticleId());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public PageInfo<Comment> selects(Article article, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Comment> list = mapper.selects(article);
		return new PageInfo<Comment>(list);
	
	}

	@Override
	public PageInfo<Article> selectsByCommentNum(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> list = mapper.selectsByCommentNum();
		return new PageInfo<Article>(list);
	}

}
