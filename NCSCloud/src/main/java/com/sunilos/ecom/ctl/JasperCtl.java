package com.sunilos.ecom.ctl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sunilos.common.BaseCtl;
import com.sunilos.common.UserContext;
import com.sunilos.ecom.dto.OrderDTO;
import com.sunilos.ecom.form.OrderForm;
import com.sunilos.ecom.service.OrderServiceInt;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/jspr")
@CrossOrigin(origins = "*")
public class JasperCtl  {

	
	@GetMapping("/jspr")
	public void print(HttpServletRequest request, HttpServletResponse response) {
		try {                                                              
			JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\siddh\\OneDrive\\Desktop\\liveProject0\\NCSCloud\\src\\main\\report\\order123.jrxml");
			
		//	HttpSession session = request.getSession(true);
			//UserDTO dto = (UserDTO) session.getAttribute("user");
			//dto.getFirstName();
			//dto.getLastName();
			
			Map<String, Object> map = new HashMap();
			map.put("user", "by eshwer");
			Connection conn = null;
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ncs_cloud","root", "root");
		//	ResourceBundle rb = ResourceBundle.getBundle("com.rays.bundle.system");
//			
//			String Database = rb.getString("DATABASE");
//			if ("Hibernate".equalsIgnoreCase(Database)) {
//				conn = ((SessionImpl) HibDataSource.getsession()).connection();
//				
//			}
//
//			if ("JDBC".equalsIgnoreCase(Database)) {
//				conn =  JDBCDataSource.getConnection();
//			}
			

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
			response.setContentType("application/pdf");
			response.getOutputStream().write(pdf);
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
