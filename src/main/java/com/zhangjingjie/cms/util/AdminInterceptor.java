package com.zhangjingjie.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
    * @ClassName: UserInterceptor
    * @Description: TODO(个人中心的拦截器)
    * @author 张经杰
    * @date 2020年3月13日
    *
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//如果注册用户已经登陆则不拦截
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("admin");
		if(null!=obj) {
			return true;//不拦截
		}else {
			//response.sendRedirect("/passport/login");
			request.setAttribute("msg", "请先登录！！！");
			request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
			return false;//拦截
		}
	}
}
