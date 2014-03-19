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
        System.out.println("Warming up");
        warmUp();
        System.out.println("Giving the JIT some time=");
        Thread.sleep(8000);
        long result=0;
        System.out.println("Starting");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        System.out.println("Collecting: Single Thread");
        result = new SingleThread().run();
        dataset.setValue(result, "ms", "Single Thread");
        System.out.println("result "+result+" ms");

        System.out.println("Collecting: 1 Thread w/ Lock ");
        result = new SingleThreadWithLock().run();
        dataset.setValue(result, "ms", "1 Thread w/ Lock");
        System.out.println("result "+result+" ms");

        System.out.println("Collecting: 2 Threads w/ Lock");
        result = new TwoThreadsWithLock().run();
        dataset.setValue(result, "ms", "2 Threads w/ Lock");
        System.out.println("result "+result+" ms");

        System.out.println("Collecting: 1 Thread w/ CAS");
        result = new SingleThreadWithCAS().run();
        dataset.setValue(result, "ms", "1 Thread w/ CAS");
        System.out.println("result "+result+" ms");

        System.out.println("Collecting: 2 Threads w/ CAS");
        result = new TwoThreadsWithCAS().run();
        dataset.setValue(result, "ms", "2 Threads w/ CAS");
        System.out.println("result "+result+" ms");

        System.out.println("Collecting: Single Thread w/ Volatile");
        result = new SingleThreadWithVolatile().run();
        dataset.setValue(result, "ms", "Single Thread w/ Volatile");
        System.out.println("result "+result+" ms");

        System.out.println("Painting picture");
        JFreeChart chart = ChartFactory.createBarChart3D("Lock Performance (500 000 000 writes)",
                "Test Type", "ms", dataset, PlotOrientation.HORIZONTAL,
                false, true, false);
        ChartUtilities.saveChartAsJPEG(new File("results.jpg"), chart, 1024, 768);
    }

    private static void warmUp() throws Exception{
        new SingleThread().run();
        new SingleThreadWithLock().run();
        new TwoThreadsWithLock().run();
        new SingleThreadWithCAS().run();
        new TwoThreadsWithCAS().run();
        new SingleThreadWithVolatile().run();
    }
}
