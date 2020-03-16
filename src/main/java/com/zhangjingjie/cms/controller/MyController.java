package com.zhangjingjie.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zhangjingjie.cms.domain.Article;
import com.zhangjingjie.cms.domain.Collect;
import com.zhangjingjie.cms.domain.User;
import com.zhangjingjie.cms.service.ArticleService;
import com.zhangjingjie.cms.service.CollectService;

/**
 * 
    * @ClassName: MyController
    * @Description: TODO(个人中心)
    * @author 张经杰
    * @date 2020年3月4日
    *
 */
@RequestMapping("my")
@Controller
public class MyController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CollectService collectService;
	
	/**
	 * 
	    * @Title: index
	    * @Description: TODO(进入个人中心的首页)
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "my/index";
	}
	/**
	 * 
	    * @Title: articles
	    * @Description: TODO(我的文章)
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("articles")
	public String articles(Model m,@RequestParam(defaultValue = "1")Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize,HttpSession session) {
		Article article = new Article();
		User user = (User)session.getAttribute("user");
		article.setUserId(user.getId());
		PageInfo<Article> info = articleService.selects(article, pageNum, pageSize);
		m.addAttribute("info", info);
		return "my/articles";
	}
	
	
	@RequestMapping("collect")
	public String collect(Model m,HttpSession session) {
		User u = (User) session.getAttribute("user");
		List<Collect> list = collectService.selects(u.getId());
		for (Collect collect : list) {
			System.err.println(collect.getText());
		}
		m.addAttribute("collect", list);
		return "my/collect";
	}
	
	/**
	 * 
	    * @Title: publish
	    * @Description: TODO(去发布文章)
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@GetMapping("publish")
	public String publish() {
		return "my/publish";
	}
	
	/**
	 * 
	    * @Title: publish
	    * @Description: TODO(发布文章)
	    * @param @param file
	    * @param @param article
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(MultipartFile file,Article article,HttpSession session) {
		//文件上传
		if(null!=file&&!file.isEmpty()) {
			String path = "d:/pic/";
			//原始名称
			String realName = file.getOriginalFilename();
			//防止重名，改变文件名字
			String newFilename = UUID.randomUUID()+realName.substring(realName.lastIndexOf("."));
			File f = new File(path,newFilename);
			
			try {
				file.transferTo(f);
				article.setPicture(newFilename);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			User user = (User)session.getAttribute("user");
			article.setUserId(user.getId());
			article.setCreated(new Date());
			article.setHits(0);//点击量默认0
			article.setDeleted(0);//默认为删除
			article.setHot(0);//默认非热门
			article.setStatus(0);//默认待审核
		}
		return articleService.insert(article)>0;
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
		return articleService.select(id);
	}
}
