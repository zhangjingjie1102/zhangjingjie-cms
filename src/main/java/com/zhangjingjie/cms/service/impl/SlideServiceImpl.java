package com.zhangjingjie.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangjingjie.cms.dao.SlideMapper;
import com.zhangjingjie.cms.domain.Slide;
import com.zhangjingjie.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService {
	
	@Autowired
	private SlideMapper mapper;

	@Override
	public List<Slide> selects() {
		return mapper.selects();
	}

}
