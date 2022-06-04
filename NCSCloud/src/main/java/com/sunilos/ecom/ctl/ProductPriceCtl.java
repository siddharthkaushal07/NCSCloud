package com.sunilos.ecom.ctl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.ecom.dto.ProductDTO;
import com.sunilos.ecom.dto.ProductModelDTO;
import com.sunilos.ecom.dto.ProductPriceDTO;
import com.sunilos.ecom.dto.SubCategoryDTO;
import com.sunilos.ecom.form.ProductPriceForm;
import com.sunilos.ecom.service.CategoryServiceInt;
import com.sunilos.ecom.service.ProductModelServiceInt;
import com.sunilos.ecom.service.ProductPriceServiceInt;
import com.sunilos.ecom.service.ProductServiceInt;
import com.sunilos.common.*;

@RestController
@RequestMapping(value = "ProductPrice")
public class ProductPriceCtl extends BaseCtl<ProductPriceForm, ProductPriceDTO, ProductPriceServiceInt> {

	
	@Autowired
	private ProductServiceInt productService;
	@Autowired
	private ProductModelServiceInt productmodelService;

	@Autowired
	private EntityManager em;

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<ProductDTO> list = productService.search(new ProductDTO(), userContext);
		List<ProductModelDTO> list1 = productmodelService.search(new ProductModelDTO(), userContext);
		res.addResult("productList", list);
		res.addResult("productmodelList", list1);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		ProductPriceDTO dto = baseService.findByName(name, userContext);
		System.out.println("ProductPrice " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	@GetMapping("getByPId/{id}")
	public ORSResponse getByPId(@PathVariable Long id) {
		ORSResponse res = new ORSResponse(true);
		ProductPriceDTO dto = baseService.findByPId(id, userContext);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	@PostMapping("getByFilter/{price}")
	public ORSResponse FilterByMRP(@PathVariable String price) {
		System.out.println("In getByFilter  " + price);
		ORSResponse res = new ORSResponse();
		String arr[] = price.split("-");
		String a = arr[0];
		String b = arr[1];

		Integer i1 = Integer.parseInt(a);
		Integer i2 = Integer.parseInt(b);
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ProductPriceDTO> cq = cb.createQuery(ProductPriceDTO.class);
		Root<ProductPriceDTO> from = cq.from(ProductPriceDTO.class);

		List<Predicate> predicates = new ArrayList<>();

		if (a != null && b != null) {
			predicates.add(cb.between((Expression) from.get("mrp"), i1, i2));
		}
		cq.where(predicates.toArray(new Predicate[0]));

		TypedQuery<ProductPriceDTO> query = em.createQuery(cq);

		List list = query.getResultList();
		res.addData(list);
		
		return res;

	}

}
