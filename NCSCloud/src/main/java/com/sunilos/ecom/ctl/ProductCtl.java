package com.sunilos.ecom.ctl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sunilos.common.*;
import com.sunilos.common.attachment.AttachmentServiceInt;
import com.sunilos.common.ctl.CtlDelegate;
import com.sunilos.common.form.DocForm;
import com.sunilos.ecom.dto.CatalogueDTO;
import com.sunilos.ecom.dto.CategoryDTO;
import com.sunilos.ecom.dto.DomainDTO;
import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.SubCategoryDTO;
import com.sunilos.ecom.form.ProductForm;
import com.sunilos.ecom.service.CategoryServiceInt;
import com.sunilos.ecom.service.DomainServiceInt;
import com.sunilos.ecom.service.ProductServiceInt;
import com.sunilos.ecom.service.SubCategoryServiceInt;

@RestController
@RequestMapping(value = "Product")
public class ProductCtl extends BaseCtl<ProductForm, ProductDTO, ProductServiceInt> {

	@Autowired
	private CategoryServiceInt categoryService;
	
	@Autowired
	private SubCategoryServiceInt subcategoryService;
	
	@Autowired
	private CtlDelegate ctlDelegate;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<CategoryDTO> list = categoryService.search(new CategoryDTO(), userContext);
		List<SubCategoryDTO> list1 = subcategoryService.search(new SubCategoryDTO(), userContext);
		res.addResult("categoryList", list);
		res.addResult("subcategoryList", list1);
		
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		ProductDTO dto = baseService.findByName(name, userContext);
		System.out.println("ProductManagement " + dto);
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
			ProductDTO cDTO = baseService.findById(product_id, userContext);
			ProductDTO dto = new ProductDTO(); 
			
			DocForm docForm = new DocForm();
			docForm.setAppId(appId);
			docForm.setUserId(userContext.getUserId());
			docForm.setOrgId(userContext.getOrgId());
//			docForm.setSkey(pDTO.getImageId());
			
			
			ORSResponse resp = ctlDelegate.upload(docForm, file, req);
			
			LinkedHashMap data = (LinkedHashMap) resp.getResult().get("data");
			System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+data.get("skey"));
			Long skey = (Long) data.get("skey");
			
			
			cDTO.setImage_id(skey);
			cDTO.setId(product_id);
			
			baseService.save(cDTO, userContext);
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
