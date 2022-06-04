package com.sunilos.ecom.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sunilos.ecom.dto.SupplierDTO;
import com.sunilos.ecom.form.SupplierForm;
import com.sunilos.ecom.service.SupplierServiceInt;
import com.sunilos.common.*;

@RestController
@RequestMapping(value = "Supplier")
public class SupplierCtl extends BaseCtl<SupplierForm, SupplierDTO, SupplierServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		SupplierDTO dto = baseService.findByName(name,userContext);
		System.out.println("Supplier " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
