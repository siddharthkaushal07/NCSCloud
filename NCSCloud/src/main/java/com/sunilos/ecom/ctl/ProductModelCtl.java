package com.sunilos.ecom.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.ProductModelDTO;
import com.sunilos.ecom.form.ProductModelForm;
import com.sunilos.ecom.service.ProductModelServiceInt;
import com.sunilos.ecom.service.ProductServiceInt;
import com.sunilos.common.*;

@RestController
@RequestMapping(value = "ProductModel")
public class ProductModelCtl extends BaseCtl<ProductModelForm, ProductModelDTO, ProductModelServiceInt> {

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
		ProductModelDTO dto = baseService.findByName(name,userContext);
		System.out.println("ProductModel " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
