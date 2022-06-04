package com.sunilos.common.ctl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sunilos.common.ORSResponse;
import com.sunilos.common.form.DatabaseForm;

/**
 * Display application internal information
 * 
 * @api \appinfo
 * @author Sunil Sahu
 * @Since Nov 20
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = { "database" })
public class DatabaseCtl {

	private static Logger log = LoggerFactory.getLogger(DatabaseCtl.class);

	@Autowired
	DataSource dataSource;

	@PersistenceContext
	protected EntityManager entityManager;

	@Value("${spring.datasource.url}")
	private String dbURL = null;

	@Value("${spring.datasource.driver-class-name}")
	private String dbDriver = null;

	@Value("${spring.datasource.username}")
	private String dbLogin = null;

	@Value("${spring.datasource.password}")
	private String dbPassword = null;

	/**
	 * Displays details of request, session, application attributes
	 * 
	 * @api \
	 * @method GET
	 * 
	 * @param HttpServletRequest
	 * @return ORSResponse
	 */
	@RequestMapping(value = { "/sql" }, method = { RequestMethod.POST })
	public ORSResponse get(@RequestBody DatabaseForm dbForm) {

		System.out.println("DatabaseCtl");
		System.out.println("dbURL" + dbURL);
		System.out.println("dbDriver" + dbDriver);
		System.out.println("dbLogin" + dbLogin);
		System.out.println("dbPassword" + dbPassword);

		System.out.println("Data source is -----------------11------->" + dataSource);
		
		com.mysql.jdbc.Driver d = null;

		ORSResponse res = new ORSResponse(true);

		String query = dbForm.getSql();
		int limit = dbForm.getLimit();

		if (query == null || query.trim().length() == 0) {
			res = new ORSResponse(false);
			res.addMessage("Query can not be null");
			return res;
		}

		try {

			String[] columns = new String[1];
			List<Object[]> rows = new ArrayList<Object[]>();

			Class.forName(dbDriver);
			// Make a connection
			//Connection conn = DriverManager.getConnection(dbURL, dbLogin, dbPassword);
			Connection conn = dataSource.getConnection();
			// Create a Statement
			Statement stmt = conn.createStatement();

			if (!query.toLowerCase().startsWith("select")) {
				int count = stmt.executeUpdate(query);
				res.addData(dbForm);
				res.addResult("columns", columns);
				res.addResult("rows", rows);
				res.addResult("colCount", 0);
				res.addResult("rowCount", 0);
				res.addMessage( count+  " record(s) are executed");
				return res;
			}

			if (limit > 0) {
				query = query + " limit " + limit;
			}

			// Execute a query
			ResultSet rs = stmt.executeQuery(query);

			ResultSetMetaData rsmt = rs.getMetaData();

			int colCount = rsmt.getColumnCount();
			columns = new String[colCount];
			for (int i = 1; i <= colCount; i++) {
				columns[i - 1] = rsmt.getColumnName(i);
			}

			while (rs.next()) {
				String[] colValues = new String[colCount];
				for (int i = 1; i <= colCount; i++) {
					colValues[i - 1] = rs.getString(i);
				}
				rows.add(colValues);
			}

			res.addData(dbForm);
			res.addResult("columns", columns);
			res.addResult("rows", rows);
			res.addResult("colCount", colCount);
			res.addResult("rowCount", rows.size());

		} catch (Exception e1) {
			res = new ORSResponse(false);
			res.addMessage(e1.getMessage());
			res.addData(dbForm);
			log.error("Error", e1);
		}

		return res;
	}

}
