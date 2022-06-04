package com.sunilos.common.ctl;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sunilos.common.ORSResponse;

@ControllerAdvice
public class ExceptionCtl {

	@ExceptionHandler(value = Exception.class)
	public ORSResponse exception(Exception ex) {
		ORSResponse res = new ORSResponse(true);
		res.setSuccess(false);
		res.addMessage(ex.getMessage());
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		res.addResult("errortrace", errors);
		return res;
	}
	
}
