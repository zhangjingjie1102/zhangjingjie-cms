package com.zhangjingjie.cms.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhangjingjie.cms.domain.Article;
import com.zhangjingjie.cms.domain.Comment;

public interface CommentService {
	/**
	 * 
	    * @Title: insert
	    * @Description: TODO(增加评论的作用)
	    * @param @param comment
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int insert(Comment comment);
	
	
	/**
	 * 
	    * @Title: selects
	    * @Description: TODO(根据文章查询文章评论)
	    * @param @return    参数
	    * @return List<Comment>    返回类型
	    * @throws
	 */
	PageInfo<Comment> selects(Article article,Integer pageNum,Integer pageSize);
	
	/**
	 * 
	    * @Title: selects
	    * @Description: TODO(评论排行榜数量排序)
	    * @param @param article
	    * @param @return    参数
	    * @return List<Comment>    返回类型
	    * @throws
	 */
	PageInfo<Article> selectsByCommentNum(Integer pageNum,Integer pageSize);
}
