package com.sunilos.common.ctl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.common.UserContext;
import com.sunilos.common.dto.UserDTO;
import com.sunilos.common.form.ChangePasswordForm;
import com.sunilos.common.form.LoginForm;
import com.sunilos.common.form.MyProfileForm;
import com.sunilos.common.form.UserRegistrationForm;
import com.sunilos.common.service.AppConfigServiceInt;

/**
 * Provides SSO login api to other micro services of the Suite. Client has to
 * specify following properties in application.properties
 * 
 * ncs.sso.authKey: http://nenosystems.in:9092/NCSSSO/Auth/authkey
 * ncs.sso.authlogin: http://nenosystems.in:9092/NCSSSO/Auth/authLogin/
 * ncs.sso.signUp: http://nenosystems.in:9092/NCSSSO/Auth/signUp
 * ncs.app.id=1592561507957
 * 
 * @author Sunil Sahu
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = { "Auth/sso", "auth/sso" })
public class SSOLoginCtl {

	private static Logger log = LoggerFactory.getLogger(CtlDelegate.class);

	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * Contains SKEY of application
	 */
	@Value("${ncs.app.id}")
	private long appId;

	@Value("${ncs.sso.auth}")
	private String authApi;

	@Autowired
	AppConfigServiceInt appConfig = null;

	private long getLongValue(Object o) {
		Long l = 0L;
		if (o == null) {
			return l;
		} else {
			try {
				l = (long) (Integer) o;
			} catch (Exception e) {
				l = 0L;
			}
		}
		return l;
	}

	/**
	 * Get Usercontext from ORSresponse
	 * 
	 * @param response
	 * @return
	 */
	private UserContext getContext(ORSResponse response, HttpSession session) {

		UserContext context = new UserContext();

		UserDTO dto = new UserDTO();
		LinkedHashMap data = (LinkedHashMap) response.getResult().get("data");
		ArrayList<LinkedHashMap> roles = (ArrayList) data.get("roles");
		ArrayList<LinkedHashMap> orgList = (ArrayList) data.get("orgList");
		LinkedHashMap setting = (LinkedHashMap) data.get("setting");

		System.out.println("------->SSO Setting org list" + orgList);
		session.setAttribute("roleList", roles);
		session.setAttribute("orgList", orgList);
		session.setAttribute("setting", setting);

		// session.setAttribute("recordsPerPage",
		// setting.get("recordsPerPage"));
		// session.setAttribute("role1", roles.get(0).get("value"));

		context.setRoles(roles);
		context.setOrgList(orgList);

		// User Info
		context.setSkey(getLongValue(data.get("skey")));
		context.setUserId(getLongValue(data.get("userId")));
		context.setLoginId((String) data.get("loginId"));
		context.setName((String) data.get("name"));
		context.setFirstName((String) data.get("firstName"));
		context.setLastName((String) data.get("lastName"));
		context.setImageId(getLongValue(data.get("imageId")));

		System.out.println("Login SSSO --> " + data);
		System.out.println("Login SSSO1 --> " + data.get("authKey"));
		context.setAuthKey((Long) data.get("authKey"));
		System.out.println("Login SSSO12 --> " + context.getAuthKey());

		// Role Indo
		context.setRoleName((String) data.get("roleName"));
		context.setDefaultRoleId(getLongValue(data.get("defaultRoleId")));

		// Org Info
		context.setOrgId(getLongValue(data.get("orgId")));
		context.setOrgName((String) data.get("orgName"));
		context.setOrgLogoId(getLongValue(data.get("orgLogoId")));

		// App Info
		context.setAppId(getLongValue(data.get("appId")));
		context.setAppName((String) data.get("appName"));
		context.setAppLogoId(getLongValue(data.get("appLogoId")));

		return context;
	}

	/**
	 * Login with NCSSSO
	 * 
	 * @param form
	 * @param bindingResult
	 * @param session
	 * @return
	 */
	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult bindingResult, HttpSession session) {

		System.out.println("login from sso loginCTL========================**************");
		form.setAppId(appId);

		ORSResponse res = BaseCtl.validate(bindingResult);
		if (!res.isSuccess()) {
			return res;
		}

		String url = authApi + "login";

		System.out.println("SSO Calling " + url);

		// Step 1: Get auth key
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<LoginForm> entity = new HttpEntity<LoginForm>(form, headers);
		ORSResponse response = restTemplate.exchange(url, HttpMethod.POST, entity, ORSResponse.class).getBody();

		System.out.println("Success  " + response.isSuccess());

		if (!response.isSuccess()) {
			return response;
		}

		UserContext context = getContext(response, session);
		session.setAttribute("userContext", context);
		response.addResult("jsessionid", session.getId());

		return response;
	}

	/**
	 * Login by security key
	 * 
	 * @param skey
	 * @param session
	 * @return
	 */
	@GetMapping("login/{skey}")
	public ORSResponse loginByAuthKey(@PathVariable long skey, HttpSession session) {

		ORSResponse res = new ORSResponse();

		String url = authApi + "/login/" + appId + "/" + skey;
		System.out.println("URL : " + url);

		// Step 1: Get auth key
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<LoginForm> entity = new HttpEntity<LoginForm>(null, headers);
		ORSResponse response = restTemplate.exchange(url, HttpMethod.GET, entity, ORSResponse.class).getBody();

		if (!response.isSuccess()) {
			return response;
		}

		UserContext context = getContext(response, session);
		session.setAttribute("userContext", context);
		response.addResult("jsessionid", session.getId());
		return response;
	}

	/**
	 * Logout user by skey
	 * 
	 * @param skey
	 * @param session
	 * @return
	 */
	@GetMapping("logout/{skey}")
	public void logoutByAuthKey(@PathVariable long skey, HttpServletResponse res, HttpSession session) {
		session.invalidate();
		String url = authApi + "logout/" + appId + "/" + skey;
		httpGet(url, res);
	}

	/**
	 * Sign up a new user
	 * 
	 * Required data:
	 * 
	 * { "lastName": "Nayna", "firstName": "Sahu", "login": "nayna@sunrays.co.in",
	 * "password": "pass1234", "role": "Employee", "appId": "1617332561558",
	 * "orgId": "1608544749223" }
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("signup")
	public void signUp(@RequestBody UserRegistrationForm form, HttpServletResponse res) {
		form.setAppId(appId);
		String url = authApi + "signup";
		httpPost(url, form, res);
	}

	/**
	 * Get Menu items for the Role
	 * 
	 * @param appId
	 * @param roleId
	 * @param res
	 */
	@GetMapping("/menu/{appId}/{roleId}")
	public void getMenu(@PathVariable long appId, @PathVariable long roleId, HttpServletResponse res) {
		String url = authApi + "menu/" + appId + "/" + roleId;
		httpGet(url, res);
	}

	/**
	 * Update user profile using auth key
	 * 
	 * @param authKey
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("profile/{authKey}")
	public void updateProfile(@PathVariable Long authKey, @RequestBody MyProfileForm form, HttpServletResponse res) {
		String url = authApi + "profile/" + appId + "/" + authKey;
		httpPost(url, form, res);
	}

	/**
	 * Change password by user auth key
	 * 
	 * @param appId
	 * @param authKey
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("cp/{authKey}")
	public void changePassword(@PathVariable Long authKey, @RequestBody ChangePasswordForm form,
			HttpServletResponse res) {
		String url = appConfig.getValue("ncs.sso.auth", null) + "cp/" + appId + "/" + authKey;
		httpPost(url, form, res);
	}

	/**
	 * Forgot the password
	 * 
	 * @param loginId
	 * @param res
	 */
	@PostMapping("fp")
	public void forgotPassword(@RequestBody LoginForm form, HttpServletResponse res) {
		form.setAppId(appId);
		String url = appConfig.getValue("ncs.sso.auth", null);
		url = url + "fp";
		httpPost(url, form, res);
	}

	/**
	 * Get user info by Authkey
	 * 
	 * @param authKey
	 * @param res
	 */
	@GetMapping("user/{authKey}")
	public void getUser(@PathVariable Long authKey, HttpServletResponse res) {
		String url = authApi + "user/" + authKey;
		httpGet(url, res);
	}

	/**
	 * Get user profile by authkey
	 * 
	 * @param authKey
	 * @return
	 */
	@GetMapping("profile/{authKey}")
	public void myProfile(@PathVariable Long authKey, HttpServletResponse res) {
		String url = authApi + "profile/" + appId + "/" + authKey;
		httpGet(url, res);
	}

	/**
	 * Get the application configuration
	 * 
	 * @param authKey
	 * @param res
	 */
	@GetMapping("appconfig/{authKey}")
	public void appConfigParams(@PathVariable Long authKey, HttpServletResponse res) {
		String url = authApi.replace("auth", "appconfig/params") + authKey;
		httpGet(url, res);
	}

	/**
	 * Upload user image
	 * 
	 * @param form
	 * @param multipartFile
	 * @param req
	 * @return
	 */

	@PostMapping("/image/{authKey}")
	public ORSResponse uploadImage(@PathVariable Long authKey, @RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest req) {
		ORSResponse res = new ORSResponse(true);

		File tempFolder = (File) req.getServletContext().getAttribute("javax.servlet.context.tempdir");
		File fileTo = new File(tempFolder, multipartFile.getOriginalFilename());
		System.out.println("File path" + fileTo.getAbsolutePath());

		// Copy file to tempfolder
		try {
			multipartFile.transferTo(fileTo);
		} catch (Exception e) {
			log.error("Error:", e);
			res.setSuccess(false);
			res.addMessage(e.getMessage());
			return res;
		}

		FileSystemResource fileResource = new FileSystemResource(fileTo);

		String url = appConfig.getValue("ncs.sso.auth", null);
		url = url + "image/" + appId + "/" + authKey;

		System.out.println("Server URL" + url);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", fileResource);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ORSResponse> response = restTemplate.postForEntity(url, requestEntity, ORSResponse.class);
		res = response.getBody();

		fileTo.delete();

		return res;
	}

	/**
	 * Send HTTP POST request
	 * 
	 * @param url
	 * @param entity
	 * @param res
	 */

	private void httpPost(String url, Object form, HttpServletResponse res) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity entity = new HttpEntity(form, headers);
		String json = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		res.setContentType("text/json");
		try {
			PrintWriter out = res.getWriter();
			out.print(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send HTTP GET request
	 * 
	 * @param url
	 * @param res
	 */
	private void httpGet(String url, HttpServletResponse res) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity entity = new HttpEntity(null, headers);
		String json = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
		res.setContentType("text/json");
		try {
			PrintWriter out = res.getWriter();
			out.print(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
