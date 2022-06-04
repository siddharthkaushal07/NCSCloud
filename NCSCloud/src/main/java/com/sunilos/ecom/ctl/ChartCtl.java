package com.sunilos.ecom.ctl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.sunilos.common.ORSResponse;
import com.sunilos.common.attachment.AttachmentDTO;
import com.sunilos.common.attachment.AttachmentServiceInt;
import com.sunilos.ecom.dto.CategoryDTO;
import com.sunilos.ecom.form.CategoryForm;
import com.sunilos.ecom.service.CategoryServiceInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sunilos.common.*;

@RestController
@RequestMapping(value = "Chart")
public class ChartCtl {

	@Autowired
	AttachmentServiceInt attachmentService;

	@GetMapping("/Image")
	public ORSResponse imageConvert() {
		System.out.println("run----");
		File f = new File("F:\\image\\medals.png");
		String encodstring = encodeFileToBase64Binary(f);
		System.out.println(encodstring);

		ORSResponse res = new ORSResponse();
		res.setSuccess(true);
		res.addResult("imgB", encodstring);
		return res;
	}

	private static String encodeFileToBase64Binary(File file) {
		String encodedfile = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fileInputStreamReader.read(bytes);
			encodedfile = Base64.encodeBase64(bytes).toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encodedfile;
	}

	@GetMapping("/getPic")
	public @ResponseBody void downloadPic(HttpServletResponse response) throws IOException {
		System.out.println("1");
		BarChart barchart = new BarChart();
		try {
			barchart.chart();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		File file = new File("F:\\image\\medals.png");
		System.out.println("2");
		FileInputStream input = new FileInputStream(file);
		MultipartFile multi = new MockMultipartFile("file", input);
//			FileItemFactory factory = new DiskFileItemFactory(16, null);
//			 FileItem item=factory.createItem(file.getName(),"text/plain",true,file.getName());
//		 MultipartFile multipartFile = new CommonsMultipartFile(item);

		AttachmentDTO attachmentDTO = new AttachmentDTO(multi);
		System.out.println("3");
		try {
			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			} else {
				response.getWriter().write("ERROR: File not found");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}