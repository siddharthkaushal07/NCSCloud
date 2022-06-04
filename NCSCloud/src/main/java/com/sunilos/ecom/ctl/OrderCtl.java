package com.sunilos.ecom.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.common.dto.RoleDTO;
import com.sunilos.ecom.dto.OrderDTO;
import com.sunilos.ecom.form.OrderForm;
import com.sunilos.ecom.service.OrderServiceImpl;
import com.sunilos.ecom.service.OrderServiceInt;

@RestController
@RequestMapping(value = "Order")

public class OrderCtl extends BaseCtl<OrderForm, OrderDTO, OrderServiceInt> {

	@Autowired
	OrderServiceImpl orderServiceInt;
	
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		
		List<OrderDTO> list = orderServiceInt.search(new OrderDTO(), userContext);
		//HashSet<OrderDTO> set=new HashSet<>(list);
		res.addResult("oList", list);
		return res;
	}
	
	
	


	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		OrderDTO dto = baseService.findByName(name, userContext);
		System.out.println("OrderManagement " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}
	

//	@GetMapping("/getMax")
//	public TypedQuery<OrderDTO> getMax() {
//		System.out.println("inside orderctl max===============");
//		return orderServiceInt.getMaxOrder();
//	}
	
	
	
	
	
	
	
	
	
//	@GetMapping("orderNo")
//	public ORSResponse orderNoList() {
//	ORSResponse res = new ORSResponse(true);
//	List list = orderServiceInt.getOrderNo();
//	System.out.println("OrderManagement "  );
//	if (list != null) {
//	res.addData(list);
//	} else {
//	res.setSuccess(false);
//	res.addMessage("Record not found");
//	}
//	return res;
//	}

	
//	@PostMapping("/saveOrder")
//	public ORSResponse save(@RequestBody @Valid OrderForm form, BindingResult bindingResult) {
//
//		System.out.println("inside save base ctl----------------");
//		ORSResponse res = validate(bindingResult);
//
//		if (res.isSuccess() == false) {
//			return res;
//		}
//
//		try {
//			System.out.println("Form---------------------------------------------->" + form);
//			OrderDTO dto = (OrderDTO) form.getDto();
//			
//			System.out.println("DTO id ==---------------------------------------------->" + dto.getId());
//			if (dto.getId() != null && dto.getId() > 0) {
//				System.out.println("calling update--------------");
//				baseService.update(dto, userContext);
//			} else {
//				System.out.println("============inside base Add========================");
//				
//				
//				baseService.add(dto, userContext);
//			}
//			res.addData(dto.getId());
//		} catch (Exception e) {
//			res.setSuccess(false);
//			res.addMessage(e.getMessage());
//			e.printStackTrace();
//		}
//		return res;
//	}


}
