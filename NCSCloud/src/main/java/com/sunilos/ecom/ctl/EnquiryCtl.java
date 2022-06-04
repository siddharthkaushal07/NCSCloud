package com.sunilos.ecom.ctl;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.EnquiryDTO;
import com.sunilos.ecom.form.EnquiryForm;
import com.sunilos.ecom.service.EnquiryServiceInt;

@RestController
@RequestMapping(value = "Enquiry")
public class EnquiryCtl extends BaseCtl<EnquiryForm, EnquiryDTO, EnquiryServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
//		 res= validate(bindingresult);
		EnquiryDTO dto = baseService.findByName(name, userContext);
		System.out.println("enquiry " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
