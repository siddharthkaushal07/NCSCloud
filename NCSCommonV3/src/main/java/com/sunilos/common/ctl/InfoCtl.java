package com.sunilos.common.ctl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.ORSResponse;

/**
 * Display application internal information
 * 
 * @api \appinfo
 * @author Sunil Sahu
 * @Since Nov 20
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = { "appinfo" })
public class InfoCtl {

	private static Logger log = LoggerFactory.getLogger(InfoCtl.class);

	private boolean ignoreKey(String key) {
		if (key == null || key.startsWith("org.springframework") || key.startsWith("org.apache")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Displays details of request, session, application attributes
	 * 
	 * @api \
	 * @method GET
	 * 
	 * @param HttpServletRequest
	 * @return ORSResponse
	 */
	@GetMapping
	public ORSResponse get(HttpServletRequest req) {
		ORSResponse res = new ORSResponse(true);
		// res.addResult("yml.ncs", ncs);

		HashMap<String, Object> reqAttributes = new HashMap<>();
		Enumeration<String> e = req.getAttributeNames();
		String key = null;
		Object val = null;
		while (e.hasMoreElements()) {
			key = e.nextElement();
			if (!ignoreKey(key)) {
				val = req.getAttribute(key);
				reqAttributes.put(key, val);
			}
		}
		res.addResult("request", reqAttributes);

		HashMap<String, Object> sessionAttributes = new HashMap<>();
		HttpSession session = req.getSession();
		e = session.getAttributeNames();

		while (e.hasMoreElements()) {
			key = e.nextElement();
			if (!ignoreKey(key)) {
				val = session.getAttribute(key);
				sessionAttributes.put(key, val);
			}
		}
		res.addResult("session", sessionAttributes);

		HashMap<String, Object> appAttributes = new HashMap<>();
		ServletContext ctx = req.getServletContext();
		e = ctx.getAttributeNames();

		while (e.hasMoreElements()) {
			key = e.nextElement();
			if (!ignoreKey(key)) {
				val = ctx.getAttribute(key);
				appAttributes.put(key, val);
			}
		}
		res.addResult("application", appAttributes);
		return res;
	}

	/**
	 * Read configuration file from classpath
	 * 
	 * @param name
	 *            file name
	 * @param res
	 */
	@GetMapping("/config")
	public @ResponseBody void properties(@RequestParam String name, HttpServletResponse res) {
		ClassPathResource resourceFile = new ClassPathResource(name);

		PrintWriter out = null;

		try {
			out = res.getWriter();
		} catch (IOException e) {
			log.error("Error", e);
		}

		try {
			File f = resourceFile.getFile();
			if (f.exists()) {
				FileReader in = new FileReader(f);
				char[] buffer = new char[(int) f.length()];
				in.read(buffer);
				out.write(buffer);
			} else {
				out.println("File does not exist");
				out.close();
			}
		} catch (Exception e) {
			out.println("File does not exist");
			out.close();
		}
	}

}
