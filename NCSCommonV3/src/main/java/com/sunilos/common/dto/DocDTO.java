package com.sunilos.common.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.sunilos.common.BaseDTO;

@Entity
@Table(name = "NCS_DOCS")
public class DocDTO extends BaseDTO {
	
	public DocDTO(MultipartFile file) {
		name = file.getOriginalFilename();
		type = file.getContentType();
	}

	public DocDTO() {
	}

	@Column(name = "USER_ID")
	protected Long userId = null;

	/**
	 * Contains name of file
	 */
	@Column(name = "NAME", length = 100)
	protected String name = null;

	/**
	 * Contains MIME type of file
	 */
	@Column(name = "TYPE", length = 100)
	protected String type = null;

	/**
	 * Contains file description
	 */
	@Column(name = "DESCRIPTION", length = 500)
	protected String description = null;

	/**
	 * Contains tags to be searched
	 */
	@Column(name = "TAGS", length = 500)
	protected String tags = null;

	/**
	 * Contains PATH of controller
	 */
	@Column(name = "PATH", length = 500)
	protected String path = null;

	@Column(name = "FILE_NAME", length = 100)
	protected String fileName = null;

	@Column(name = "SIZE")
	protected long size = 0;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFilePath() {
		return path + fileName;
	}

	public String getThumbnailPath() {
		return path + "TH" + fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String getValue() {
		return id + "";
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("name", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
