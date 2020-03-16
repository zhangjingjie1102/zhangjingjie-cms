package com.zhangjingjie.cms.service;

import com.github.pagehelper.PageInfo;
import com.zhangjingjie.cms.domain.Article;

public interface ArticleService {
	/**
	 * 
	    * @Title: insert
	    * @Description: TODO(增加文章)
	    * @param @param article
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int insert(Article article);
	
	/**
	 * 
	    * @Title: selects
	    * @Description: TODO(文章列表)
	    * @param @param article
	    * @param @return    参数
	    * @return List<Article>    返回类型
	    * @throws
	 */
	PageInfo<Article> selects(Article article,Integer pageNum,Integer pageSize);
	
	/**
	 * 
	    * @Title: select
	    * @Description: TODO(单个文章)
	    * @param @param id
	    * @param @return    参数
	    * @return Article    返回类型
	    * @throws
	 */
	Article select(Integer id);
	
	/**
	 * 
	    * @Title: update
	    * @Description: TODO(更新)
	    * @param @param article
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int update(Article article);
}
