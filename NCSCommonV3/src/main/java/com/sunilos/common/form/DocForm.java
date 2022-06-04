package com.sunilos.common.form;

import com.sunilos.common.BaseDTO;
import com.sunilos.common.BaseForm;
import com.sunilos.common.dto.DocDTO;

public class DocForm extends BaseForm {

	public DocForm() {
	}

	public DocForm(long appId, long orgId, long userId) {
		this.appId = appId;
		this.orgId = orgId;
		this.userId = userId;
	}

	protected Long userId = null;

	/**
	 * Contains name of file
	 */
	protected String name = null;

	/**
	 * Contains MIME type of file
	 */
	protected String type = null;

	/**
	 * Contains file description
	 */
	protected String description = null;

	/**
	 * Contains tags to be searched
	 */
	protected String tags = null;

	/**
	 * Contains PATH of controller
	 */
	protected String path = null;

	/**
	 * Contains size of File
	 */
	protected long size = 0;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public BaseDTO getDto() {
		DocDTO dto = initDTO(new DocDTO());
		dto.setName(name);
		dto.setType(type);
		dto.setDescription(description);
		dto.setTags(tags);
		dto.setPath(path);
		dto.setSize(size);
		dto.setUserId(userId);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		super.populate(bDto);
		DocDTO dto = (DocDTO) bDto;
		name = dto.getName();
		type = dto.getType();
		description = dto.getDescription();
		tags = dto.getTags();
		path = dto.getPath();
		size = dto.getSize();
		userId = dto.getUserId();
	}

}
