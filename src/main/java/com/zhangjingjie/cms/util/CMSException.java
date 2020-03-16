package com.zhangjingjie.cms.util;
/**
 * 
    * @ClassName: CMSException
    * @Description: TODO(自定义异常)
    * @author 张经杰
    * @date 2020年3月12日
    *
 */
public class CMSException extends RuntimeException{

	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 1L;
	private String message;//异常消息
	
	public CMSException() {
		
	}
	
	public CMSException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
