package com.sunilos.common.gen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenCode {

	public static String USERCASE_NAME = "OrderItem";

	public static String TEMP_DIR = "E:\\NCS_Cloud\\NCSCommonV3\\src\\main\\java\\com\\sunilos\\common\\gen/";

	public static String TARGET_DIR = "E:\\NCS_Cloud\\NCSCloud\\src\\main\\java/";

	// public static String dir = "G:/sunRays/workspace/NCSB2B/src/main/java/";

	public static String basePakage = "com.sunilos.ecom";

	public static String getDir(String subPakage) {
		return "/" + subPakage.substring(7, subPakage.length() - 1).trim().replace(".", "/");

	}

	public static void generateClass(String inFile, String outFile, String pkg) throws IOException {

		inFile = TEMP_DIR + inFile;

		String dir = TARGET_DIR + basePakage.replace(".", "/") + "/" + pkg + "/";

		Path path = Paths.get(dir);

		// java.nio.file.Files;
		Files.createDirectories(path);

		outFile = dir + USERCASE_NAME + outFile;

		System.out.println("Taget File " + outFile + ":" + dir);

		BufferedReader in = new BufferedReader(new FileReader(inFile));
		PrintWriter out = new PrintWriter(new FileWriter(outFile));

		String line = in.readLine();

		while (line != null) {
			if (line.trim().startsWith("package")) {
				line = line.replace("com.sunilos.common", basePakage);
				// line = "package " + basePakage + ";";
				out.println(line);
				line = in.readLine();
				break;
			}
			line = in.readLine();
		}

		while (line != null) {
			line = line.replace("Product", USERCASE_NAME);
			line = line.replace("PRODUCT", USERCASE_NAME.toUpperCase());
			out.println(line);
			line = in.readLine();
		}
		in.close();
		out.close();

	}

	public static void main(String[] args) throws Exception {

		// DTO
		generateClass("dto.txt", "DTO.java", "dto");
		System.out.println("DTO is generated");

		// Form
		generateClass("form.txt", "Form.java", "form");
		System.out.println("Form bean is generated");

		generateClass("daoint.txt", "DAOInt.java", "dao");
		generateClass("daoimpl.txt", "DAOImpl.java", "dao");
		System.out.println("DAO is generated");

		// Service
		generateClass("serviceint.txt", "ServiceInt.java", "service");

		generateClass("serviceimpl.txt", "ServiceImpl.java", "service");

		System.out.println("Service is generated");

		generateClass("ctl.txt", "Ctl.java", "ctl");

		System.out.println("Controller is generated");

	}

}
