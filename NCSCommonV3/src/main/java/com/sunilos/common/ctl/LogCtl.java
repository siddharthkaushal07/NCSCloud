package com.sunilos.common.ctl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.ORSResponse;

/**
 * View log files and manipulate them controller.
 * 
 * @author Sunil Sahu
 * @since 2020
 * 
 * @see api \log
 *
 */
@RestController
@RequestMapping(value = { "log" })
@CrossOrigin(origins = "*")
public class LogCtl {

	// Document source
	@Value("${ncs.doc.path}")
	public String path = null;

	/**
	 * Get list of files of a folder
	 * 
	 * @param dirName
	 * @return
	 */
	private List<String> getFileList(String dirName) {

		List<String> files = new ArrayList<String>();

		File dir = new File(dirName);
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
			if (file.isFile()) {
				files.add(file.getName());
			}
		}

		/*
		 * try (Stream<Path> walk = Files.walk(Paths.get(logFolder))) { files =
		 * walk.filter(Files::isRegularFile).map(x ->
		 * x.getFileName().toString()).collect(Collectors.toList());
		 * files.forEach(System.out::println); } catch (IOException e) {
		 * res.addMessage(e.getMessage()); e.printStackTrace(); }
		 */

		return files;
	}

	/**
	 * Send file to the response
	 * 
	 * @param fileath
	 * @param response
	 */
	private void sendFile(String fileath, HttpServletResponse response) {
		try {
			OutputStream out = response.getOutputStream();
			response.setContentType("text/plain");
			try {
				Path path = Paths.get(fileath);
				out.write(Files.readAllBytes(path));
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				response.getWriter().write("ERROR: " + e.getMessage());
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ORSResponse deleteFile(String path, String file) {

		ORSResponse res = new ORSResponse(true);

		File f = new File(path + "/" + file);
		if (f.exists()) {
			if (f.delete()) {
				res.setSuccess(true);
				res.addMessage("File " + file + " is deleted");
			} else {
				res.setSuccess(false);
				res.addMessage("File " + file + " can not be deleted");
			}
		} else {
			res.setSuccess(false);
			res.addMessage("File " + file + " does not exist");
			return res;
		}

		return res;
	}

	/**
	 * Get list of log files
	 * 
	 * @api \
	 * @method GET
	 * 
	 * @param req
	 * @return
	 */
	@GetMapping
	public ORSResponse logList(HttpServletRequest req) {
		ORSResponse res = new ORSResponse(true);
		List<String> files = getFileList(path + "/log");
		List<String> afiles = getFileList(path + "/log/archived");
		File tempFolder = (File) req.getServletContext().getAttribute("javax.servlet.context.tempdir");
		List<String> tempFile = getFileList(tempFolder.getAbsolutePath());

		res.addData(files);
		res.addResult("archived", afiles);
		res.addResult("temp", tempFile);
		return res;
	}

	/**
	 * Display Log file
	 * 
	 * @param name
	 * @param response
	 */
	@GetMapping("/file")
	public @ResponseBody void logFile(@RequestParam String name, HttpServletResponse response) {
		String logFolder = path + "/log/" + name;
		sendFile(logFolder, response);
	}

	/**
	 * Display quick view of log file
	 * 
	 * @api \view\{filename}
	 * @method GET
	 * @param name
	 *            name of file
	 * @param response
	 */
	@GetMapping("/view")
	public @ResponseBody void quickLogView(@RequestParam String name, HttpServletResponse response) {

		String fileName = path + "/log/" + name;
		try {
			int size = 1024 * 6;
			Path path = Paths.get(fileName);
			byte[] buffer = Files.readAllBytes(path);

			// System.out.println(new String(buffer));
			// Get last {{size}} bytes
			byte b1[] = null;
			if (buffer.length > size) {
				b1 = Arrays.copyOfRange(buffer, buffer.length - size, buffer.length);
			} else {
				b1 = buffer;
			}

			// Convert bytes into lines
			ArrayList<String> list = new ArrayList<String>();
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(b1)));
			String line = in.readLine();
			while (line != null) {
				list.add(line);
				line = in.readLine();
			}
			// Reverse the lines
			Collections.reverse(list);
			PrintWriter out = new PrintWriter(response.getOutputStream());
			response.setContentType("text/plain");
			int i = 0;
			while (i < list.size()) {
				out.println(list.get(i));
				i++;
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a log file
	 * 
	 * @api \delete
	 * @method GET/POST
	 * @param file
	 *            : name of file
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse deleteLogFile(@RequestParam String file) {
		return deleteFile(path + "/log", file);
	}

	/**
	 * Get list of archived log files
	 * 
	 * @return
	 */
	@GetMapping("/archived")
	public ORSResponse archivedList() {
		ORSResponse res = new ORSResponse(true);
		List<String> files = getFileList(path + "/log/archived");
		res.addData(files);
		return res;
	}

	/**
	 * Download aerchived log file
	 * 
	 * @see api= \{fileName} method= GET
	 * 
	 * @param name:
	 *            name of file
	 * @param response
	 */
	@GetMapping("/archived/file")
	public @ResponseBody void archivedFile(@RequestParam String name, HttpServletResponse response) {
		String logFolder = path + "/log/archived/" + name;
		sendFile(logFolder, response);
	}

	@RequestMapping(value = "/archived/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse deleteArchivedFile(@RequestParam String file) {
		return deleteFile(path + "/log/archived", file);
	}

	/**
	 * List files from temp folder
	 * 
	 * @param req
	 * @return
	 */
	@GetMapping("/temp")
	public ORSResponse getTemp(HttpServletRequest req) {
		ORSResponse res = new ORSResponse(true);
		File tempFolder = (File) req.getServletContext().getAttribute("javax.servlet.context.tempdir");
		List<String> files = getFileList(tempFolder.getAbsolutePath());
		res.addData(files);
		return res;
	}

	@GetMapping("/temp/file")
	public @ResponseBody void tempFile(@RequestParam String file, HttpServletRequest req,
			HttpServletResponse response) {
		File f = (File) req.getServletContext().getAttribute("javax.servlet.context.tempdir");
		String logFolder = f.getAbsolutePath() + "/" + file;
		sendFile(logFolder, response);
	}

	/**
	 * Delete file from Temp folder
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/temp/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse deleteTemp(@RequestParam String file, HttpServletRequest req) {
		File f = (File) req.getServletContext().getAttribute("javax.servlet.context.tempdir");
		return deleteFile(f.getAbsolutePath(), file);
	}

	/**
	 * Test controller
	 * 
	 * @param args
	 */

	public static void main(String[] args) throws Exception {
	}

}
