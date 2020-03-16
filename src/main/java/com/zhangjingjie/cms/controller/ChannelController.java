package com.zhangjingjie.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangjingjie.cms.domain.Category;
import com.zhangjingjie.cms.domain.Channel;
import com.zhangjingjie.cms.service.ChannelService;

@Controller
@RequestMapping("channel")
public class ChannelController {
	
	@Autowired
	private ChannelService service;
	
	@ResponseBody
	@RequestMapping("channels")
	public List<Channel> channels(){
		return service.selects();
	}
	
	
	@ResponseBody
	@RequestMapping("selectsByChannelId")
	public List<Category> selectsByChannelId(Integer channelId){
		return service.selectsByChannelId(channelId);
	}
}
