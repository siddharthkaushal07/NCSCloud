package com.sunilos.common.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sunilos.common.dto.ErrorLogDTO;
import com.sunilos.common.form.ErrorLogForm;
import com.sunilos.common.service.ErrorLogServiceInt;
import com.sunilos.common.*;

@RestController
@RequestMapping(value = { "ErrorLog", "errorlog"})
public class ErrorLogCtl extends BaseCtl<ErrorLogForm, ErrorLogDTO, ErrorLogServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

}
