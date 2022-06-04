package com.sunilos.common.ctl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.DropDownListAdapter;
import com.sunilos.common.ORSResponse;
import com.sunilos.common.dto.AppConfigDTO;
import com.sunilos.common.dto.UserDTO;
import com.sunilos.common.form.AppConfigForm;
import com.sunilos.common.service.AppConfigServiceInt;

@RestController
@RequestMapping(value = { "AppConfig", "appconfig" })
public class AppConfigCtl extends BaseCtl<AppConfigForm, AppConfigDTO, AppConfigServiceInt> {

	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		return res;
	}

	@GetMapping("param/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		AppConfigDTO dto = baseService.findByParamName(name, userContext);
		System.out.println("AppConfig " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	@GetMapping("params/{authKey}")
	public ORSResponse get(@PathVariable long authKey) {

		ORSResponse res = new ORSResponse(true);

		UserDTO dto = baseService.getUserByAuthKey(authKey);

		if (dto == null) {
			res.setSuccess(false);
			res.addMessage("Invalid authkey");
			return res;
		}

		AppConfigDTO searchDto = new AppConfigDTO();
		List<AppConfigDTO> clist = baseService.search(searchDto, userContext);

		List<DropDownListAdapter> list = clist.stream().map(e -> {
			return new DropDownListAdapter(e.getParamName(), e.getParamValue());
		}).collect(Collectors.toList());

		res.addData(list);
		return res;
	}

}
