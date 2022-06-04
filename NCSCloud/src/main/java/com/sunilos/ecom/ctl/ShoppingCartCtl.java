package com.sunilos.ecom.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.ProductModelDTO;
import com.sunilos.ecom.dto.ProductPriceDTO;
import com.sunilos.ecom.dto.ShoppingCartDTO;
import com.sunilos.ecom.form.ShoppingCartForm;
import com.sunilos.ecom.service.ProductModelServiceInt;
import com.sunilos.ecom.service.ProductPriceServiceImpl;
import com.sunilos.ecom.service.ProductServiceInt;
import com.sunilos.ecom.service.ShoppingCartServiceInt;

@RestController
@RequestMapping(value = "Shopping")
public class ShoppingCartCtl extends BaseCtl<ShoppingCartForm, ShoppingCartDTO, ShoppingCartServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		ShoppingCartDTO dto = baseService.findByName(name, userContext);
		System.out.println("ShoppingCart " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	@Autowired
	ProductModelServiceInt pms=null;
	
	@Autowired
	ProductPriceServiceImpl pps=null;
	
	@Autowired
	ProductServiceInt ps;
	
	@PostMapping("addItem")
	public ORSResponse addItem(@RequestBody ShoppingCartForm form) {
		System.out.println("inside addItem----------------------");
		
	
		ORSResponse res = new ORSResponse(true);
		ShoppingCartDTO scdto=(ShoppingCartDTO) form.getDto();
	
		System.out.println("name========="+scdto.getProductName());
		System.out.println("quantity=============="+scdto.getQuantity());
		System.out.println("imageId.============" + scdto.getImage_id());

		ProductDTO pdto= ps.findByName(scdto.getProductName(), userContext);
		
		ProductModelDTO pmdto= pms.findByName(scdto.getProductName(), userContext);
		ProductPriceDTO ppdto=pps.findByName(scdto.getProductName(), userContext);
		//System.out.println("get model no============->>>>>>>>>>>>>>>>> "+pmdto.getModel_no());
		scdto.setModel_no(pmdto.getModel_no());
		
		scdto.setPrice(ppdto.getPrice());
		scdto.setGst(ppdto.getGst());
		try {
			
		
		long id=baseService.save(scdto, userContext);
		res.setSuccess(true);
		res.addData(id);
	}
		catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
			e.printStackTrace();
		}
		return res;
		
		
	}
	
}
