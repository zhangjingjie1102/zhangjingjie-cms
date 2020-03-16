package com.zhangjingjie.cms.service;

import com.github.pagehelper.PageInfo;
import com.zhangjingjie.cms.domain.User;

public interface UserService {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 用户列表
	 * @param user
	 * @return
	 * @return: List<User>
	 */
	PageInfo<User> selects(User user,Integer pageNum,Integer pageSize);
	
	/**
	 * 
	 * @Title: update 
	 * @Description: 更新
	 * @param user
	 * @return
	 * @return: int
	 */
	int update(User user);
	/**
	 * 
	    * @Title: insert
	    * @Description: TODO(新增用户)
	    * @param @param user
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int insert(User user);
	
	/**
	 * 
	    * @Title: selectByUsername
	    * @Description: TODO(根据用户名查询用户是否存在)
	    * @param @param username
	    * @param @return    参数
	    * @return User    返回类型
	    * @throws
	 */
	User selectByUsername(String username);
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录
	 * @param user
	 * @return
	 * @return: User
	 */
	User login(User user); 
}
