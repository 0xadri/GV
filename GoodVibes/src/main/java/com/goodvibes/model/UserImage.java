package com.goodvibes.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "USER_IMAGE")
public class UserImage {

	@Id
	@GeneratedValue
	@Column(name="USER_IMAGE_ID")
	private Integer imageId;
    
    @Column(name="TYPE", nullable = false)
    private int type;
    
    @Column(name="NAME", length = 50)
    private String name;
    
    @Column(name="CONTENT_TYPE", length = 50, nullable = false)
    private String contentType;
    
    @Column(name="LENGTH", nullable = false)
    private Integer length;
    
	@Column(name="CONTENT")
    @Lob
    private Blob content;
	
	
    public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}

	public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
	
}
