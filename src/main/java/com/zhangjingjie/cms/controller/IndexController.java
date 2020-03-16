package com.zhangjingjie.cms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhangjingjie.cms.domain.Article;
import com.zhangjingjie.cms.domain.Category;
import com.zhangjingjie.cms.domain.Channel;
import com.zhangjingjie.cms.domain.Collect;
import com.zhangjingjie.cms.domain.Comment;
import com.zhangjingjie.cms.domain.Slide;
import com.zhangjingjie.cms.domain.User;
import com.zhangjingjie.cms.service.ArticleService;
import com.zhangjingjie.cms.service.ChannelService;
import com.zhangjingjie.cms.service.CollectService;
import com.zhangjingjie.cms.service.CommentService;
import com.zhangjingjie.cms.service.SlideService;
@Controller
public class IndexController {
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private SlideService slideService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CollectService collectService;
	
	@RequestMapping(value = {"","/","index"})
	public String index(Model m,Article article,@RequestParam(defaultValue = "1")Integer pageNum,
			@RequestParam(defaultValue = "4")Integer pageSize) {
		article.setStatus(1);
		article.setDeleted(0);
		//判断是否点击左侧热点栏目
		if(article.getChannelId()==null) {
			article.setHot(1);
			//轮播图
			List<Slide> slides = slideService.selects();
			m.addAttribute("slides", slides);
		}
		
		//封装查询条件
		m.addAttribute("article", article);
		
		//查询左侧栏目
		List<Channel> channels = channelService.selects();
		m.addAttribute("channels", channels);
		
		//如果栏目Id不为空查询其下分类
		if(article.getChannelId()!=null) {
			List<Category> categorys = channelService.selectsByChannelId(article.getChannelId());
			m.addAttribute("categorys", categorys);
		}
		
		//查询所有的文章
		PageInfo<Article> info = articleService.selects(article, pageNum, pageSize);
		m.addAttribute("info", info);
		
		
		
		//在右侧显示最新10篇问文章
		Article article2 = new Article();
		article2.setStatus(1);
		article2.setDeleted(0);
		PageInfo<Article> lastArticles = articleService.selects(article2, 1, 10);
		m.addAttribute("lastArticles", lastArticles);
		return "index/index";
	}
	
	/**
	 * 
	    * @Title: articleDetail
	    * @Description: TODO(文章详情)
	    * @param @param id
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("articleDetail")
	public String articleDetail(Integer id,Model m,@RequestParam(defaultValue = "1")Integer pageNum,
			@RequestParam(defaultValue = "4")Integer pageSize,HttpSession session) {
		Article article = articleService.select(id);
		m.addAttribute("article", article);
		//查询出当前文章的评论
		
		PageInfo<Comment> info = commentService.selects(article, pageNum, pageSize);
		//查询所有文章的评论
		PageInfo<Article> info2 = commentService.selectsByCommentNum(pageNum, pageSize);
		m.addAttribute("info", info);
		m.addAttribute("info2", info2);
		
		//查询文章是否被收藏过
		User user = (User) session.getAttribute("user");
		Collect collect = null;
		if(null!=user) {//如果用户已经登陆则查询是否被收藏过
			 collect = collectService.selectByTitleAndUserId(article.getTitle(), user.getId());
		}
		m.addAttribute("collect", collect);
		return "index/articleDetail";
	}
	
	
	//增加评论
	@ResponseBody
	@RequestMapping("addComment")
	public boolean addComment(Comment comment,HttpSession session) {
		User u = (User)session.getAttribute("user");
		if(null==u)
			return false;
		comment.setUserId(u.getId());
		comment.setArticleId(comment.getArticleId());
		comment.setCreated(new Date());
		return commentService.insert(comment)>0;
	}
	
	//收藏文章
		@ResponseBody
		@RequestMapping("collect")
		public boolean collect(Collect collect,HttpSession session) {
			User u = (User)session.getAttribute("user");
			if(null==u)
				return false;//没有登陆的用户不能收藏
			collect.setUser(u);
			collect.setCreated(new Date());
			return collectService.insert(collect)>0;
		}
		
		//取消收藏文章
		@ResponseBody
		@RequestMapping("deleteCollect")
		public boolean deleteCollect(Integer id) {
			return collectService.delete(id)>0;
		}
	
}
