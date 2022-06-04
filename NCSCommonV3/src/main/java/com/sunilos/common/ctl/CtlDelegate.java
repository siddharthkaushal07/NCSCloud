package com.sunilos.common.ctl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.sunilos.common.ORSResponse;
import com.sunilos.common.form.DocForm;

/**
 * 
 * @author Sunil Sahu
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = { "api/del" })
public class CtlDelegate {

	private static Logger log = LoggerFactory.getLogger(CtlDelegate.class);

	@Value("${ncs.doc.url}")
	private String docUrl;

	/**
	 * Public url to upload a document
	 * 
	 * @param form
	 * @param multipartFile
	 * @param req
	 * @return
	 */

	@PostMapping("/doc/up")
	public ORSResponse upload(@ModelAttribute DocForm form, @RequestParam("file") MultipartFile multipartFile,
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

		String serverUrl = docUrl + "up";

		System.out.println("Server URL" + serverUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", fileResource);
		body.add("appId", form.getAppId());
		body.add("orgId", form.getOrgId());
		body.add("userId", form.getUserId());
		body.add("skey", form.getSkey());

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ORSResponse> response = restTemplate.postForEntity(serverUrl, requestEntity, ORSResponse.class);
		res = response.getBody();

		fileTo.delete();

		return res;
	}

	/**
	 * Get a document from security key
	 * 
	 * @param skey
	 * @param res
	 */
	@GetMapping("/doc/{skey}")
	public @ResponseBody void download(@PathVariable long skey, HttpServletResponse res) {
		String serverUrl = docUrl + skey;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<byte[]> response = restTemplate.exchange(serverUrl, HttpMethod.GET, entity, byte[].class);
			res.setContentType(response.getHeaders().getContentType().toString());
			res.setContentLength((int) response.getHeaders().getContentLength());
			OutputStream out = res.getOutputStream();
			out.write(response.getBody());
			out.close();
		} catch (Exception e) {
			log.error("Error:", e);
		}

	}

	/**
	 * Get thumbnail of a document
	 * 
	 * @param skey
	 * @param res
	 */
	@GetMapping("/doc/th/{skey}")
	public @ResponseBody void downloadthumbnail(@PathVariable long skey, HttpServletResponse res) {
		String serverUrl = docUrl + "th/" + skey;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<byte[]> response = restTemplate.exchange(serverUrl, HttpMethod.GET, entity, byte[].class);
			res.setContentType(response.getHeaders().getContentType().toString());
			res.setContentLength((int) response.getHeaders().getContentLength());
			OutputStream out = res.getOutputStream();
			out.write(response.getBody());
			out.close();
		} catch (Exception e) {
			log.error("Error:", e);
		}

	}


	/**
	 * Unit test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// G:\temp\ncssuitedoc\docs\THA43\O22\U26\FMTYwNTYyMTgyNDUzNw==.jpg
		String str = "G:\\temp\\ncssuitedoc\\docs\\A43\\O22\\U26\\FMTYwNTYyMTgyNDUzNw==.jpg";
		System.out.println(str);
		String str1 = str.substring(0, str.lastIndexOf("\\") + 1) + "TH" + str.substring(str.lastIndexOf("\\") + 1);
		System.out.println(str1);
	}

}
