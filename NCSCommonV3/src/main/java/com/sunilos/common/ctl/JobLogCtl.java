package com.sunilos.common.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sunilos.common.dto.JobLogDTO;
import com.sunilos.common.form.JobLogForm;
import com.sunilos.common.service.JobLogServiceInt;
import com.sunilos.common.*;

@RestController
@RequestMapping(value = "JobLog")
public class JobLogCtl extends BaseCtl<JobLogForm, JobLogDTO, JobLogServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		JobLogDTO dto = baseService.findByName(name,userContext);
		System.out.println("JobLog " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
