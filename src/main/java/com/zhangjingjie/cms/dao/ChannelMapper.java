package com.zhangjingjie.cms.dao;

import java.util.List;

import com.zhangjingjie.cms.domain.Category;
import com.zhangjingjie.cms.domain.Channel;

public interface ChannelMapper {
	
	/**
	 * 
	    * @Title: selects
	    * @Description: TODO(查询所有栏目)
	    * @param @return    参数
	    * @return List<Channel>    返回类型
	    * @throws
	 */
	List<Channel> selects();
	
	/**
	 * 
	    * @Title: selectsByChannelId
	    * @Description: TODO(根据栏目查询分类)
	    * @param @param channelId
	    * @param @return    参数
	    * @return List<Category>    返回类型
	    * @throws
	 */
	List<Category> selectsByChannelId(Integer channelId);
}
