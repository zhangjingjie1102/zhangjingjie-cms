package com.zhangjingjie.cms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhangjingjie.cms.domain.Article;
import com.zhangjingjie.cms.domain.User;
import com.zhangjingjie.cms.service.ArticleService;
import com.zhangjingjie.cms.service.UserService;
@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	
	
	/**
	 * 
	    * @Title: index
	    * @Description: TODO(进入管理员首页)
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping("articles")
	public String articles(Article article,Model m,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "18") Integer pageSize) {
		PageInfo<Article> info = articleService.selects(article, pageNum, pageSize);
		m.addAttribute("info", info);
		m.addAttribute("article", article);
		return "admin/articles";
	}
	
	/**
	 * 
	    * @Title: update
	    * @Description: TODO(修改文章)
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	@ResponseBody
	@RequestMapping("update")
	public boolean update(Article article) {
		return articleService.update(article)>0;
	}
	
	
	/**
	 * 
	    * @Title: articleDetail
	    * @Description: TODO(查询单个文章)
	    * @param @param id
	    * @param @return    参数
	    * @return Article    返回类型
	    * @throws
	 */
	@ResponseBody
	@RequestMapping("articleDetail")
	public Article articleDetail(Integer id) {
		System.out.println(id);
		return articleService.select(id);
	}
	
	
	@RequestMapping("users")
	public String users(Model model,User user,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "18") Integer pageSize) {
		PageInfo<User> info = userService.selects(user, pageNum, pageSize);
		model.addAttribute("info", info);
		model.addAttribute("user", user);
		return "admin/users";
		
	}
	
	/**
	 * 
	    * @Title: updateUser
	    * @Description: TODO(更新用户信息)
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@ResponseBody
	@RequestMapping("updateUser")
	public boolean updateUser(User user) {
		return userService.update(user)>0;
	}
}
