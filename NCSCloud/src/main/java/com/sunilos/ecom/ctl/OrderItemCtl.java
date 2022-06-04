package com.sunilos.ecom.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.ecom.dao.OrderItemDAOInt;
import com.sunilos.ecom.dto.OrderDTO;
import com.sunilos.ecom.dto.OrderItemDTO;
import com.sunilos.ecom.dto.SubCategoryDTO;
import com.sunilos.ecom.form.OrderForm;
import com.sunilos.ecom.form.OrderItemForm;
import com.sunilos.ecom.service.OrderItemServiceInt;
import com.sunilos.ecom.service.OrderServiceInt;

@RestController
@RequestMapping(value = "OrderItem")
public class OrderItemCtl extends BaseCtl<OrderItemForm, OrderItemDTO, OrderItemServiceInt> {

	@Autowired
	OrderItemServiceInt orderItemServInt;
	
	
	
	
	@Autowired
	OrderServiceInt orderServInt;
	
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		
//		subcategoryService.search(new SubCategoryDTO(), userContext);
		List list= orderServInt.search(new OrderDTO(), userContext);
		res.addResult("orderNo",list);
		return res;
	}
	
	
	@PostMapping("/placeOrder")
	public ORSResponse placeOrder(@RequestBody @Valid OrderItemForm form, BindingResult bindingResult) {

		System.out.println("inside placeorder order ctl----------------");
		ORSResponse res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		try {
			System.out.println("order Form---------------------------------------------->" + form);
			OrderItemDTO dto = (OrderItemDTO) form.getDto();
			System.out.println("DTO id ==---------------------------------------------->" + dto.getId());
			if (dto.getId() != null && dto.getId() > 0) {
				System.out.println("calling update--------------");
				
				//baseService.update(dto, userContext);
			} else {
				System.out.println("============inside order___ctl Add========================");
				orderItemServInt.placeOrder(dto, userContext);
				//long total_price= ;
			//	System.out.println("-----------------****************----------------"+dto.getTotal_quantity());
					
//				OrderDTO odto = new OrderDTO();
//				
//				odto.setTotal_price(dto.getTotal_price());
//				odto.setOrderId(dto.getOrder_id());
//				odto.setQuantity(dto.getTotal_quantity());
//				odto.setDescription("thankyou for shopping from Rays Ecom");
//				odto.setDiscount("10%");
//				orderServInt.add(odto, userContext);
				
				//baseService.add(dto, userContext);
			}
			res.addData(dto.getId());
			res.addMessage("Successfully Saved!!");
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
			e.printStackTrace();
		}
		return res;
	}




	

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		OrderItemDTO dto = baseService.findByName(name,userContext);
		System.out.println("OrderItem " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}
	
	
	@GetMapping("orderNo")
	public ORSResponse getOrderNo() {
	ORSResponse res = new ORSResponse(true);
	List list = orderItemServInt.getOrderNo();
	System.out.println("OrderManagement "  );
	if (list != null) {
	res.addData(list);
	} else {
	res.setSuccess(false);
	res.addMessage("Record not found");
	}
	return res;
	}

}
