package com.sunilos.common;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sunilos.common.service.AppConfigServiceInt;

/**
 * Base controller class contains get, search, save, delete REST APIs
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 * @param <F>
 * @param <T>
 * @param <S>
 */

@CrossOrigin(origins = "*")
public abstract class BaseCtl<F extends BaseForm, T extends BaseDTO, S extends BaseServiceInt<T>> {

	/**
	 * Form operations
	 */
	protected static final String OP_SAVE = "Save";
	protected static final String OP_NEW = "New";
	protected static final String OP_DELETE = "Delete";
	protected static final String OP_CANCEL = "Cancel";
	protected static final String OP_ERROR = "Error";
	protected static final String OP_NEXT = "Next";
	protected static final String OP_PREVIOUS = "Previous";
	protected static final String OP_LOGOUT = "Logout";
	protected static final String OP_GO = "Go";
	protected static final String OP_GET = "Get";

	@Autowired
	protected S baseService = null;

	@Value("${ncs.page.size}")
	protected int pageSize = 0;

	/**
	 * Contains context of logged-in user
	 */
	public UserContext userContext = null;

	
	@Autowired
	protected AppConfigServiceInt  appConfig = null;
	
	/**
	 * Get user context from session
	 * 
	 * @param session
	 */
	@ModelAttribute
	public void setUserContext(HttpSession session) {
		userContext = (UserContext) session.getAttribute("userContext");
	}

	/**
	 * Default get mapping
	 * 
	 * @return
	 */
	@GetMapping
	public ORSResponse get() {
		ORSResponse res = new ORSResponse(true);
		res.addData("I am okay " + this.getClass() + " --" + new Date());
		return res;
	}

	/**
	 * Exception response
	 * 
	 * @param ex
	 * @return
	 */
	private ORSResponse exceptionResponse(Exception ex) {
		ex.printStackTrace();
		ORSResponse res = new ORSResponse(true);
		res.setSuccess(false);
		res.addMessage(ex.getMessage());
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		res.addResult("errortrace", errors);
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable Long id) {
		
		System.out.println("inside findbyPK basectl--------------");
		ORSResponse res = new ORSResponse(true);
		T dto = baseService.findById(id, userContext);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	/**
	 * Delete entity by primary key ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping({ "delete/{id}", "d/{id}" })
	public ORSResponse delete(@PathVariable long id) {

		ORSResponse res = new ORSResponse(true);
		try {
			T dto = baseService.delete(id, userContext);
			res.addData(dto);
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
		}
		return res;
	}

	/**
	 * Get entity by security key
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("g/{sky}")
	public ORSResponse getSkey(@PathVariable Long skey) {
		ORSResponse res = new ORSResponse(true);
		try {
			T dto = baseService.findBySkey(skey, userContext);
			if (dto != null) {
				res.addData(dto);
			} else {
				res.setSuccess(false);
				res.addMessage("Record not found");
			}
		} catch (Exception ex) {
			res = exceptionResponse(ex);
		}
		return res;
	}

	/**
	 * Get preload data for the View
	 * 
	 * @return
	 */

	@GetMapping({ "/preload", "/p" })
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		// Sample code
		// List<DropdownList> orglist = (List<DropdownList>)
		// orgService.search(orgDto, userContext);
		// res.addResult("orglist", orglist);
		return res;
	}

	/**
	 * Search entities by form attributes withput pagination
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody F form) {
		T dto = (T) form.getDto();
		ORSResponse res = new ORSResponse(true);
		try {
				System.out.println("search inside base ctl------------===========");
			List l = baseService.search(dto, userContext);
			
			int recCount = l.size();
			res.addData(l);
			res.addResult("pageSize", pageSize);
			res.addResult("recordCount", recCount);
			int pageCount = (int) Math.round((recCount / pageSize) + 0.5);
			res.addResult("pageCount", pageCount);
		} catch (Exception ex) {
			res = exceptionResponse(ex);
		}

		return res;
	}

	/**
	 * Search and apply pagination
	 * 
	 * @param form
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody F form, @PathVariable int pageNo) {

		// 0 is first page index
		pageNo = (pageNo < 0) ? 0 : pageNo;

		System.out.println(pageNo + "Page size is****************" + pageSize);

		T dto = (T) form.getDto();

		 ORSResponse res = new ORSResponse(true);

		try {
			List l = baseService.search(dto, pageNo, pageSize, userContext);

			Long recCount = (Long) l.remove(l.size() - 1);

			res.addData(l);
			res.addResult("recordCount", recCount);
			res.addResult("pageSize", pageSize);
			int pageCount = (int) Math.round((recCount / pageSize) + 0.5);
			res.addResult("pageCount", pageCount);
		} catch (Exception ex) {
			res = exceptionResponse(ex);
		}

		return res;
	}

	/**
	 * Add or Update an entity. If ID is greater than ZERO then it will update
	 * otherwise add
	 * 
	 * @param form
	 * @param bindingResult
	 * @return
	 */

	@PostMapping("/save")
	public ORSResponse save(@RequestBody @Valid F form, BindingResult bindingResult) {

		System.out.println("inside save base ctl----------------");
		ORSResponse res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

		try {
			System.out.println("Form---------------------------------------------->" + form);
			T dto = (T) form.getDto();
			System.out.println("DTO id ==---------------------------------------------->" + dto.getId());
			if (dto.getId() != null && dto.getId() > 0) {
				System.out.println("calling update--------------");
				baseService.update(dto, userContext);
			} else {
				System.out.println("============inside base Add========================");
				baseService.add(dto, userContext);
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

	/**
	 * Gets input error messages and put into REST response
	 * 
	 * @param bindingResult
	 * @return
	 */
	public static ORSResponse validate(BindingResult bindingResult) {
		ORSResponse res = new ORSResponse(true);

		if (bindingResult.hasErrors()) {

			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();
			// Lambda expression Java 8 feature
			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});
			res.addInputErrors(errors);
		}
		return res;

	}

	/**
	 * Import CSV into table
	 * 
	 * @param file
	 * @return
	 */
	@PostMapping({ "/csvImport" })
	public ORSResponse importCsv(@RequestParam("file") MultipartFile file) {
		ORSResponse res = new ORSResponse(true);
		// Sample code
		// List<DropdownList> orglist = (List<DropdownList>)
		// orgService.search(orgDto, userContext);
		// res.addResult("orglist", orglist);
		return res;
	}

	/**
	 * Define columns of CSV file
	 * 
	 * @return
	 */
	@GetMapping({ "/csv" })
	public ORSResponse csvFormat() {
		ORSResponse res = new ORSResponse(false);
		res.addMessage("Import not alowed");
		// List if DTO attributes to be imported
		// res.addData("firstName,lastName,address");
		return res;
	}

	/**
	 * Covert CSV line data into data
	 * 
	 * @param line
	 * @return
	 */
	public T csvToDto(String line) {
		return null;
	}

	/**
	 * Export data into Excel
	 * 
	 * @param dto
	 * @param response
	 */
	@PostMapping({ "/excel" })
	public @ResponseBody void exportExcel(T dto, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		try {
			OutputStream out = response.getOutputStream();
			out.close();
		} catch (Exception e) {
		}
	}

}
