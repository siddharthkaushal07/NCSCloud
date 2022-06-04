package com.sunilos.ecom.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.DomainDTO;
import com.sunilos.ecom.form.DomainForm;
import com.sunilos.ecom.service.DomainServiceInt;

@RestController
@RequestMapping(value = "Domain")
public class DomainCtl extends BaseCtl<DomainForm, DomainDTO, DomainServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		DomainDTO dto = baseService.findByName(name, userContext);
		System.out.println("Domain " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
