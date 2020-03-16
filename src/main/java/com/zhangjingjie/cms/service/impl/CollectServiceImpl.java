package com.zhangjingjie.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangjingjie.cms.dao.CollectMapper;
import com.zhangjingjie.cms.domain.Collect;
import com.zhangjingjie.cms.service.CollectService;
import com.zhangjingjie.cms.util.CMSException;
import com.zhangjingjie.common.utils.StringUtil;
@Service
public class CollectServiceImpl implements CollectService {
	
	@Autowired
	private CollectMapper mapper;

	@Override
	public int insert(Collect collect) {
		if(!StringUtil.isHttpUrl(collect.getUrl())){
			throw new CMSException("不是合法的url");
		}
		return mapper.insert(collect);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return mapper.delete(id);
	}

	@Override
	public List<Collect> selects(Integer userId) {
		// TODO Auto-generated method stub
		return mapper.selects(userId);
	}

	@Override
	public Collect selectByTitleAndUserId(String title, Integer userId) {
		// TODO Auto-generated method stub
		return mapper.selectByTitleAndUserId(title, userId);
	}

}
