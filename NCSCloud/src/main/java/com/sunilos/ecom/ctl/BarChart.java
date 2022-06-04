package com.sunilos.ecom.ctl;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BarChart {

	private static JDBCCategoryDataset dataset;

	public static void chart() throws IOException, SQLException {

		String url = "jdbc:mysql://localhost:3306/ncscloud";
		String user = "root";
		String password = "root";

		try (Connection con = DriverManager.getConnection(url, user, password)) {

			dataset = new JDBCCategoryDataset(con);
			dataset.executeQuery("SELECT name ,price FROM ncs_productmanagement");
		}

		JFreeChart barChart = ChartFactory.createBarChart("Product Price", "Name", "Price", dataset,
				PlotOrientation.VERTICAL, false, true, false);

		ChartUtils.saveChartAsPNG(new File("F:\\image\\medals.png"), barChart, 450, 400);

		System.out.println("Chart created");
	}
}