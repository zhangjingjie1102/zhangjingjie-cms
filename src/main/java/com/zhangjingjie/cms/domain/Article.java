package com.zhangjingjie.cms.domain;

import java.util.Date;

/**
 * 
    * @ClassName: Article
    * @Description: TODO(文章表)
    * @author 张经杰
    * @date 2020年3月3日
    *
 */
public class Article {
	
	private Integer id;//主键
	
	private String title;//文章标题
	
	private String summary;//文章摘要
	
	private String content;//文章内容
	
	private String picture;//文章标题图片
	
	private Integer channelId;//所属栏目Id;
	
	private Integer categoryId;//所属分类Id;
	
	private Integer userId;//文章发布人Id
	
	private Integer hits;//点击量
	
	private Integer hot;//是否热门文章 1：热门  0：一般文章
	
	private Integer status;//文章审核状态 0待审 1审核通过 -1审核不通过
	
	private Integer deleted;//删除逻辑删除 0正常 1逻辑删除
	
	private Date updated;//文章修改时间
	
	private Channel channel;
	
	private Category category;
	
	private User user;

	private Date created;

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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", summary=" + summary + ", content=" + content + ", picture="
				+ picture + ", channelId=" + channelId + ", categoryId=" + categoryId + ", userId=" + userId + ", hits="
				+ hits + ", hot=" + hot + ", status=" + status + ", deleted=" + deleted + ", updated=" + updated
				+ ", channel=" + channel + ", category=" + category + ", user=" + user + ", created=" + created + "]";
	}
	
}
