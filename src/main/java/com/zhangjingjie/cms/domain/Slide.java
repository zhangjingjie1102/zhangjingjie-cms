package com.zhangjingjie.cms.domain;
/**
 * 
    * @ClassName: Slide
    * @Description: TODO(广告表)
    * @author 张经杰
    * @date 2020年3月3日
    *
 */
public class Slide {
	private Integer id;//主键
	
	private String title;//广告文字说明
	
	private String picture;//广告图片的地址
	
	private String url;//图片的url

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
