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
        dataset.setValue(new SingleThread().run(), "ms", "Single Thread");
        dataset.setValue(new SingleThreadWithLock().run(), "ms", "1 Thread w/ Lock");
        dataset.setValue(new TwoThreadsWithLock().run(), "ms", "2 Threads w/ Lock");
        dataset.setValue(new SingleThreadWithCAS().run(), "ms", "1 Thread w/ CAS");
        dataset.setValue(new TwoThreadsWithCAS().run(), "ms", "2 Threads w/ CAS");
        dataset.setValue(new SingleThreadWithVolatile().run(), "ms", "Single Thread w/ Volatile");
        JFreeChart chart = ChartFactory.createBarChart3D("Lock Performance (500000000 writes",
                "Test Type", "ms", dataset, PlotOrientation.HORIZONTAL,
                false, true, false);
        ChartUtilities.saveChartAsJPEG(new File("results.jpg"), chart, 1024, 768);
    }
}
