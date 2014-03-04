package de.codepitbull.lockperformance;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;

/**
 * @author Jochen Mader
 */
public class PerformanceMain {
    public static void main(String[] args) throws Exception{

        Thread.sleep(8000);
        System.out.println("Starting");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        System.out.println("Collecting: Single Thread");
        dataset.setValue(new SingleThread().run(), "ms", "Single Thread");
        System.out.println("Collecting: 1 Thread w/ Lock ");
        dataset.setValue(new SingleThreadWithLock().run(), "ms", "1 Thread w/ Lock");
        System.out.println("Collecting: 2 Threads w/ Lock");
        dataset.setValue(new TwoThreadsWithLock().run(), "ms", "2 Threads w/ Lock");
        System.out.println("Collecting: 1 Thread w/ CAS");
        dataset.setValue(new SingleThreadWithCAS().run(), "ms", "1 Thread w/ CAS");
        System.out.println("Collecting: 2 Threads w/ CAS");
        dataset.setValue(new TwoThreadsWithCAS().run(), "ms", "2 Threads w/ CAS");
        System.out.println("Collecting: Single Thread w/ Volatile");
        dataset.setValue(new SingleThreadWithVolatile().run(), "ms", "Single Thread w/ Volatile");
        System.out.println("Painting picture");
        JFreeChart chart = ChartFactory.createBarChart3D("Lock Performance (500 000 000 writes)",
                "Test Type", "ms", dataset, PlotOrientation.HORIZONTAL,
                false, true, false);
        ChartUtilities.saveChartAsJPEG(new File("results.jpg"), chart, 1024, 768);
    }
}
