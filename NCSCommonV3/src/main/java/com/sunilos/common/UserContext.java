package com.sunilos.common;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;

import com.sunilos.common.dto.SystemSettingDTO;
import com.sunilos.common.dto.UserDTO;

/**
 * Contains logged in user information
 * 
 * @author Sunil Sahu
 *
 */
public class UserContext {

	//User Info
	private Long skey = 0L;
	private Long authKey = 0L;
	private Long userId = 0L;
	private String loginId = "root";
	private String name = null;
	private String firstName;
	private String lastName;
	private Long imageId = 0L;
	private String mobileId = null;

	private Long defaultRoleId = 0L;
	private String roleName = "root";

	//Org info
	private Long orgId = 0L;
	private String orgName = "root";
	private Long orgImageId = 0L;
	private Long orgLogoId = 0L;

	//Application Info
	private Long appId = 0L;
	private String appName = "";
	private Long appLogoId = 0L;

	private UserDTO userDTO = null;

	//Other information
	private HashMap<String, Object> values = new HashMap<String, Object>();

	private List roles = null;

	private List orgList = null;

	private List appList = null;

	SystemSettingDTO setting = null;

	public UserContext() {
	}

	public UserContext(UserDTO dto) {

		// this.userDTO = dto;
		this.userId = dto.getId();
		this.loginId = dto.getLoginId();
		this.imageId = dto.getImageId();
		
		this.name = dto.getName();
		this.firstName = dto.getFirstName();
		this.lastName = dto.getLastName();
		

		this.defaultRoleId = dto.getRoleId();
		this.roleName = dto.getRoleName();

		this.orgId = dto.getOrgId();
		this.orgName = dto.getOrgName();
		this.mobileId= dto.getMobileId();
	}

	
	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getOrgLogoId() {
		return orgLogoId;
	}

	public void setOrgLogoId(Long orgLogoId) {
		this.orgLogoId = orgLogoId;
	}

	public Long getSkey() {
		return skey;
	}

	public void setSkey(Long skey) {
		this.skey = skey;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long applcationId) {
		this.appId = applcationId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDefaultRoleId() {
		return defaultRoleId;
	}

	public void setDefaultRoleId(Long defaultRoleId) {
		this.defaultRoleId = defaultRoleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgImageId() {
		return orgImageId;
	}

	public void setOrgImageId(Long orgImageId) {
		this.orgImageId = orgImageId;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAppLogoId() {
		return appLogoId;
	}

	public void setAppLogoId(Long appLogoId) {
		this.appLogoId = appLogoId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public HashMap<String, Object> getValues() {
		return values;
	}

	public void setValues(HashMap<String, Object> values) {
		this.values = values;
	}

	public void setValue(String key, Object value) {
		values.put(key, value);
	}

	public Object getValue(String key) {
		return values.get(key);
	}

	public List getRoles() {
		return roles;
	}

	public void setRoles(List roles) {
		this.roles = roles;
	}

	public List getOrgList() {
		return orgList;
	}

	public void setOrgList(List orgList) {
		this.orgList = orgList;
	}

	public SystemSettingDTO getSetting() {
		return setting;
	}

	public void setSystemSetting(SystemSettingDTO setting) {
		this.setting = setting;
	}

	public List getAppList() {
		return appList;
	}

	public void setAppList(List appList) {
		this.appList = appList;
	}

	public Long getAuthKey() {
		return authKey;
	}

	public void setAuthKey(Long authKey) {
		this.authKey = authKey;
	}

	public String getMobileId() {
		return mobileId;
	}

	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}
	
	
	

}
