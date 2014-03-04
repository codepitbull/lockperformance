package de.codepitbull.lockperformance;

/**
 * @author Jochen Mader
 */
public class SingleThreadWithVolatile {

    private volatile Long count=0l;

    public Long run() {
        long start = System.currentTimeMillis();
        for(;count<500_000_000;count++);
        return System.currentTimeMillis()-start;
    }
}
