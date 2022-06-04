package com.sunilos.common.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sunilos.common.ORSResponse;
import com.sunilos.common.UserContext;
import com.sunilos.common.dto.EmailParams;
import com.sunilos.common.dto.ErrorLogDTO;

/**
 * Email sender service is used to send email by NCSMSG microservice
 * 
 * @author Sunil Sahu
 *
 */
@Component
public class EmailSenderService {

	private RestTemplate restTemplate = new RestTemplate();

	@Autowired
	ErrorLogServiceInt errorLogService = null;

	@Value("${ncs.email.url}")
	private String emailApi;

	public ORSResponse send(EmailParams params, UserContext userContext) {

		String url = emailApi + "send";
		ORSResponse res;
		try {
			res = httpPost(url, params.getParams());
		if (!res.isSuccess()) {
				ErrorLogDTO dto = new ErrorLogDTO("Response Error:" +  res.getMessage() + "--" + res.getResult());
			errorLogService.log(dto, userContext);
		}
		} catch (Exception e) {
			res = new ORSResponse();
			res.setSuccess(false);
			res.addMessage(e.getMessage());
			errorLogService.error(e, userContext);
		}

		return res;

	}

	private ORSResponse httpPost(String url, Object form) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity entity = new HttpEntity(form, headers);
		ORSResponse response = restTemplate.exchange(url, HttpMethod.POST, entity, ORSResponse.class).getBody();
		return response;
	}

}
