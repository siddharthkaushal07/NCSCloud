package com.sunilos.ecom.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.ecom.dto.BillingDTO;
import com.sunilos.ecom.dto.ShippingDTO;
import com.sunilos.ecom.form.BillingForm;
import com.sunilos.ecom.service.BillingServiceImpl;
import com.sunilos.ecom.service.BillingServiceInt;
import com.sunilos.ecom.service.ShippingServiceInt;

@RestController
@RequestMapping(value = "Billing")
@CrossOrigin(origins = "*")
public class BillingCtl extends BaseCtl<BillingForm, BillingDTO, BillingServiceInt>{

	@Autowired
	ShippingServiceInt shippingServiceInt;
	
	@Autowired
	BillingServiceImpl billingServiceImpl;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		
		System.out.println("inside billing preload");
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse getData(@PathVariable Long id) {
		
		System.out.println("inside findbyPK basectl--------------");
		ORSResponse res = new ORSResponse(true);
		BillingDTO dto = baseService.findById(id, userContext);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}
	
	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		BillingDTO dto = baseService.findByName(name,userContext);
		System.out.println("Shipping " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}
	
	@GetMapping("bGet/{id}")
	public ORSResponse get(@PathVariable Long id) {
		
		System.out.println("inside findbyPK billing ctl");
		ORSResponse res = new ORSResponse(true);
		ShippingDTO dto = shippingServiceInt.findById(id, userContext);
		//System.out.println(dto.getAddress());
		BillingDTO bdto = new BillingDTO();
		if (dto != null) {
			bdto.setAddress(dto.getAddress());
			bdto.setName(dto.getName());
			bdto.setCity(dto.getCity());
			bdto.setLastname(dto.getLastname());
			bdto.setSid(dto.getId());
			bdto.setState(dto.getState());
			bdto.setPincode(dto.getPincode());
			bdto.setMobileno(dto.getMobileno());
			
			
			//baseService.add(dto, userContext);
			billingServiceImpl.addBill(bdto, userContext);
			res.addData(bdto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}
	
	@GetMapping("/test/{id}")
	public String test(@PathVariable Long id) {
		
		System.out.println(id+"---=-=-=-=-==-=-=-");
		return "test method call of BillingCTL";
		
	}
	
	@GetMapping("maxBid")
	public ORSResponse getOrderNo() {

		System.out.println("maxbid inside bill ctl-------- "  );

		ORSResponse res = new ORSResponse(true);
	List list = billingServiceImpl.getMaxBid();

	if (list != null) {
	res.addData(list);
	} else {
	res.setSuccess(false);
	res.addMessage("Record not found");
	}
	return res;
	}

}
