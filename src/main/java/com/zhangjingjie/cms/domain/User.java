package com.zhangjingjie.cms.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
    * @ClassName: User
    * @Description: TODO(用户信息)
    * @author 张经杰
    * @date 2020年3月3日
    *
 */
public class User implements Serializable{
	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 1L;

	private Integer id;//主键
	
	private String username;
	
	private String password;
	
	private String repassword; //确认密码
	
	private String nickname;//昵称
	
	private Date birthday;
	
	private String gender;
	
	private Integer locked;//0: 正常 1：禁用
	
	private Date created;//注册时间
	
	private Date update;//修改时间
	
	private Integer role; //用户角色
	
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public User() {}
	public User(Integer id,String username) {
		this.id=id;
		this.username = username;
	}
	
	
	

	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}
	
}
