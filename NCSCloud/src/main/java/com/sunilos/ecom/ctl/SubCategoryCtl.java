package com.sunilos.ecom.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.*;
import com.sunilos.ecom.dto.CategoryDTO;
import com.sunilos.ecom.dto.SubCategoryDTO;
import com.sunilos.ecom.form.SubCategoryForm;
import com.sunilos.ecom.service.CategoryServiceInt;
import com.sunilos.ecom.service.SubCategoryServiceInt;

@RestController
@RequestMapping(value = "SubCategory")
public class SubCategoryCtl extends BaseCtl<SubCategoryForm, SubCategoryDTO, SubCategoryServiceInt> {

	@Autowired
	private CategoryServiceInt categoryService;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<CategoryDTO> list = categoryService.search(new CategoryDTO(), userContext);
		res.addResult("categoryList", list);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		SubCategoryDTO dto = baseService.findByName(name, userContext);
		System.out.println("SubCategory " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
