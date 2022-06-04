package com.sunilos.ecom.ctl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sunilos.ecom.dto.CatalogueDTO;
import com.sunilos.ecom.dto.CategoryDTO;
import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.SubCategoryDTO;
import com.sunilos.ecom.form.CatalogueForm;
import com.sunilos.ecom.service.CatalogueServiceInt;
import com.sunilos.ecom.service.CategoryServiceInt;
import com.sunilos.ecom.service.ProductServiceInt;
import com.sunilos.ecom.service.SubCategoryServiceInt;

import ch.qos.logback.core.Context;

import com.sunilos.common.*;
import com.sunilos.common.attachment.AttachmentDTO;
import com.sunilos.common.attachment.AttachmentServiceInt;
import com.sunilos.common.ctl.CtlDelegate;
import com.sunilos.common.form.DocForm;

@RestController
@RequestMapping(value = "Catalogue")
public class CatalogueCtl extends BaseCtl<CatalogueForm, CatalogueDTO, CatalogueServiceInt> {

	@Autowired
	private ProductServiceInt productService;
	
	@Autowired
	private CtlDelegate ctlDelegate;
	
	@Autowired
	private CategoryServiceInt categoryService;
	
	@Autowired
	private SubCategoryServiceInt subcategoryService;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<ProductDTO> list = productService.search(new ProductDTO(), userContext);
		List<CategoryDTO> list1 = categoryService.search(new CategoryDTO(), userContext);
		List<SubCategoryDTO> list2 = subcategoryService.search(new SubCategoryDTO(), userContext);
		res.addResult("productList", list);
		res.addResult("categoryList", list1);
		res.addResult("subcategoryList", list2);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		CatalogueDTO dto = baseService.findByName(name,userContext);
		System.out.println("Catalogue " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	@Autowired
	AttachmentServiceInt attachmentService;

	
	@PostMapping("/productimage")
	public ORSResponse uploadPic(Long long1, @RequestParam("file") MultipartFile file, HttpServletRequest req) {
		return uploadPic(userContext.getImageId(), file, req);
	}
	@Value("${ncs.app.id}")
	private Long appId;
	
	@PostMapping("/productimage/{product_id}")
	public ORSResponse addRewardImg(@PathVariable Long product_id, @RequestParam("file")  MultipartFile file,
			HttpServletRequest req) { 
		System.out.println(file);
		ORSResponse res = new ORSResponse(true);
		
		try {
			CatalogueDTO cDTO = baseService.findById(product_id, userContext);
			CatalogueDTO dto = new CatalogueDTO(); 
			
			DocForm docForm = new DocForm();
			docForm.setAppId(appId);
			docForm.setUserId(userContext.getUserId());
			docForm.setOrgId(userContext.getOrgId());
//			docForm.setSkey(pDTO.getImageId());
			
			ORSResponse resp = ctlDelegate.upload(docForm, file, req);
			
			LinkedHashMap data = (LinkedHashMap) resp.getResult().get("data");
			System.out.println(data.get("skey"));
			Long skey = (Long) data.get("skey");
			
			cDTO.setImage_id(skey);
		
			cDTO.setProduct_id(product_id);
			baseService.save(dto, userContext);
			res.setSuccess(true);
			res.addData(userContext);
			res.addMessage("File upload succesfully");
		} catch (Exception e) {
			e.printStackTrace();
			res.setSuccess(false);
			res.addMessage(e.getMessage());
		}
		return res;	
		}
		
	
}
