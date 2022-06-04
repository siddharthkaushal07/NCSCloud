package com.sunilos.ecom.ctl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.common.UserContext;
import com.sunilos.common.dto.UserDTO;
import com.sunilos.ecom.dto.SellerRegistrationDTO;
import com.sunilos.ecom.form.SellerLoginForm;
import com.sunilos.ecom.form.SellerRegistrationForm;
import com.sunilos.ecom.service.SellerRegistrationServiceInt;

@RestController
@RequestMapping(value = "SellerRegistration")
public class SellerRegistrationCtl extends BaseCtl<SellerRegistrationForm, SellerRegistrationDTO, SellerRegistrationServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		SellerRegistrationDTO dto = baseService.findByName(name,userContext);
		System.out.println("SellerRegistration " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}
	
	
}
