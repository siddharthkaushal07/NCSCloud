package com.sunilos.ecom.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sunilos.ecom.dto.ComplainDTO;
import com.sunilos.ecom.form.ComplainForm;
import com.sunilos.ecom.service.ComplainServiceInt;
import com.sunilos.common.*;

@RestController
@RequestMapping(value = "Complain")
public class ComplainCtl extends BaseCtl<ComplainForm, ComplainDTO, ComplainServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		ComplainDTO dto = baseService.findByName(name,userContext);
		System.out.println("Complain " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
