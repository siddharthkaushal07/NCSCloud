package com.sunilos.ecom.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.OfferDTO;
import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.form.OfferForm;
import com.sunilos.ecom.service.OfferServiceInt;
import com.sunilos.ecom.service.ProductServiceInt;

@RestController
@RequestMapping(value = "Offer")
public class OfferCtl extends BaseCtl<OfferForm, OfferDTO, OfferServiceInt> {

	@Autowired
	private ProductServiceInt productService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<ProductDTO> list = productService.search(new ProductDTO(), userContext);
		res.addResult("productList", list);
		return res;

	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		OfferDTO dto = baseService.findByName(name, userContext);
		System.out.println("OfferManagement " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
