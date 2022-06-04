package com.zetcode;

import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.sunilos.ecom.dto.ProductDTO;

public class LineChartToPNGEx {

    public static void main(String[] args) throws IOException {

    	XYSeries series1 = new XYSeries("2021");
        series1.add(18, 530);
        series1.add(20, 580);
        series1.add(25, 740);
        series1.add(30, 901);
        series1.add(40, 1300);
        series1.add(50, 2219);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Product Detail",
                "Iphone",
                "Price (€)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        		);

        ChartUtils.saveChartAsPNG(new File("line_chart.png"), chart, 450, 400);
       
    }
}