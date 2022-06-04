package com.sunilos.ecom.ctl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.ORSResponse;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.SellerRegistrationDTO;
import com.sunilos.ecom.form.SellerLoginForm;
import com.sunilos.ecom.form.SellerRegistrationForm;
import com.sunilos.ecom.service.SellerRegistrationServiceInt;

@RestController
@RequestMapping(value = "SellerLogin")
public class SellerLoginCtl extends BaseCtl<SellerRegistrationForm, SellerRegistrationDTO, SellerRegistrationServiceInt>{

	
	
	
	@Autowired
	protected SellerRegistrationServiceInt sellerRegistrationServ;
	
	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid SellerLoginForm form, BindingResult br, HttpSession session)
	{
		System.out.println("inside LoginCtl post login----------");
		System.out.println("email---- " + form.getLoginId() + "password----" + form.getPassword());
		ORSResponse res=validate(br);
		if(!res.isSuccess()) {
			return res;
		}
		
	SellerRegistrationDTO dto=	baseService.authenticate(form.getLoginId(), form.getPassword());
		
		if(dto==null) {
			res.setSuccess(false);
			res.addMessage("invalid ID or Password");
			
			
		}
		
		else {
		UserContext uc= new UserContext();
		session.setAttribute("uc", uc);
		System.out.println("user context inside login ctl------- " + uc);
		res.setSuccess(true);
		res.addData(dto);
		res.addResult("jsessionid", session.getId());
		
		System.out.println("jsessionid " + session.getId());
		}

		System.out.println("Login User context : " + session.getAttribute("uc")) ;
		
		return res;
		
	}

}
