package com.sunilos.ecom.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.ecom.dto.GSTRegistrationDTO;
import com.sunilos.ecom.form.GSTRegistrationForm;
import com.sunilos.ecom.service.GSTRegistrationServiceInt;

@RestController
@RequestMapping(value = "GSTRegistration")
public class GSTRegistrationCtl extends BaseCtl<GSTRegistrationForm, GSTRegistrationDTO, GSTRegistrationServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		GSTRegistrationDTO dto = baseService.findByName(name,userContext);
		System.out.println("GSTRegistration " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
