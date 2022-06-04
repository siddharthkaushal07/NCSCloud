package com.sunilos.ecom.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.TaxDTO;
import com.sunilos.ecom.form.TaxForm;
import com.sunilos.ecom.service.TaxServiceInt;

@RestController
@RequestMapping(value = "Tax")
public class TaxCtl extends BaseCtl<TaxForm, TaxDTO, TaxServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		TaxDTO dto = baseService.findByName(name, userContext);
		System.out.println("TaxManagement " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
