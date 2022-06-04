package com.sunilos.ecom.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sunilos.ecom.dto.ShippingDTO;
import com.sunilos.ecom.form.ShippingForm;
import com.sunilos.ecom.service.ShippingServiceInt;
import com.sunilos.common.*;

@RestController
@RequestMapping(value = "Shipping")
public class ShippingCtl extends BaseCtl<ShippingForm, ShippingDTO, ShippingServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		ShippingDTO dto = baseService.findByName(name,userContext);
		System.out.println("Shipping " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
