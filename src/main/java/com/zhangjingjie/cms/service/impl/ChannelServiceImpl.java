package com.zhangjingjie.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangjingjie.cms.dao.ChannelMapper;
import com.zhangjingjie.cms.domain.Category;
import com.zhangjingjie.cms.domain.Channel;
import com.zhangjingjie.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {
	
	@Autowired
	private ChannelMapper mapper;

	@Override
	public List<Channel> selects() {
		return mapper.selects();
	}

	@Override
	public List<Category> selectsByChannelId(Integer channelId) {
		return mapper.selectsByChannelId(channelId);
	}
}
