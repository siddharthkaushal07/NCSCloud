package com.sunilos.ecom.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.CategoryDTO;
import com.sunilos.ecom.dto.DomainDTO;
import com.sunilos.ecom.form.CategoryForm;
import com.sunilos.ecom.service.CategoryServiceInt;
import com.sunilos.ecom.service.DomainServiceInt;

@RestController
@RequestMapping(value = "Category")
public class CategoryCtl extends BaseCtl<CategoryForm, CategoryDTO, CategoryServiceInt> {

	@Autowired
	private DomainServiceInt domainService;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<DomainDTO> list = domainService.search(new DomainDTO(), userContext);
		res.addResult("domainList", list);
		
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		CategoryDTO dto = baseService.findByName(name, userContext);
		System.out.println("CategoryManagement " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
