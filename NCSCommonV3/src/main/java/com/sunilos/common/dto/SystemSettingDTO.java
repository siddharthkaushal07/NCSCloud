package com.sunilos.common.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sunilos.common.BaseDTO;

/**
 * User POJO class. It is persistent object.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@Entity
@Table(name = "NCS_SYSTEM_SETTING")
public class SystemSettingDTO extends BaseDTO {

	public static String YES = "Y";
	public static String NO = "N";
	
	@Column(name = "ORG_ID")
	private Long orgId;

	@Column(name = "ORG_NAME", length = 200)
	private String orgName = null;

	@Column(name = "APP_ID")
	private Long appId;

	@Column(name = "APP_NAME", length = 200)
	private String appName = null;

	@Column(name = "RECORDS_PER_PAGE", length = 50)
	private Long recordsPerPage;

	@Column(name = "APP_VERSION", length = 10)
	private String appVersion;

	@Column(name = "APP_URL", length = 70)
	private String appURL;

	@Column(name = "ADMIN_EMAIL", length = 1000)
	private String adminEmail;

	@Column(name = "TERMS_CONDITION", length = 10000)
	private String termsCondition;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "EMAILSENDER_NAME")
	private String emailSenderName;

	@Column(name = "MENUBASE_URL")
	private String menubaseURL;

	@Column(name = "MOBILE_LOGIN")
	private Integer mobileLogin = FALSE;

	@Column(name = "FACEBOOK_LOGIN")
	private Integer facebookLogin = FALSE;

	@Column(name = "GMAIL_LOGIN")
	private Integer gmailLogin = FALSE;

	@Column(name = "CAN_READ", length = 1)
	private String canRead = YES;
	
	@Column(name = "UPLOAD_CONTACT", length = 1)
	private String uploadContact = NO;
	
	@Column(name = "UPLOAD_CALLLOG", length = 1)
	private String uploadCallLog = NO;
	
	@Column(name = "UPLOAD_LOCATION", length = 1)
	private String uploadLocation = NO;
	
	@Column(name = "UPLOAD_GALLERY", length = 1)
	private String uploadGallery = NO;
	
	public String getCanRead() {
		return canRead;
	}

	public void setCanRead(String canRead) {
		this.canRead = canRead;
	}

	public String getUploadContact() {
		return uploadContact;
	}

	public void setUploadContact(String uploadContact) {
		this.uploadContact = uploadContact;
	}

	public String getUploadCallLog() {
		return uploadCallLog;
	}

	public void setUploadCallLog(String uploadCallLog) {
		this.uploadCallLog = uploadCallLog;
	}

	public String getUploadLocation() {
		return uploadLocation;
	}

	public void setUploadLocation(String uploadLocation) {
		this.uploadLocation = uploadLocation;
	}

	public String getUploadGallery() {
		return uploadGallery;
	}

	public void setUploadGallery(String uploadGallery) {
		this.uploadGallery = uploadGallery;
	}
	
	public Long getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(Long recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getTermsCondition() {
		return termsCondition;
	}

	public void setTermsCondition(String termsCondition) {
		this.termsCondition = termsCondition;
	}

	public String getEmailSenderName() {
		return emailSenderName;
	}

	public void setEmailSenderName(String emailSenderName) {
		this.emailSenderName = emailSenderName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppURL() {
		return appURL;
	}

	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return null;
	}

	public String getMenubaseURL() {
		return menubaseURL;
	}

	public void setMenubaseURL(String menubaseURL) {
		this.menubaseURL = menubaseURL;
	}

	public Integer getMobileLogin() {
		return mobileLogin;
	}

	public void setMobileLogin(Integer mobileLogin) {
		this.mobileLogin = mobileLogin;
	}

	public Integer getFacebookLogin() {
		return facebookLogin;
	}

	public void setFacebookLogin(Integer facebookLogin) {
		this.facebookLogin = facebookLogin;
	}

	public Integer getGmailLogin() {
		return gmailLogin;
	}

	public void setGmailLogin(Integer gmailLogin) {
		this.gmailLogin = gmailLogin;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("appName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		//map.put("appId", appId);
		return map;
	}

	@Override
	public boolean isGroupFilter() {
		return false;
	}

}
