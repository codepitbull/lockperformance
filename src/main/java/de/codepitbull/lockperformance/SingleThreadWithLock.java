package de.codepitbull.lockperformance;

/**
 * @author Jochen Mader
 */
public class SingleThreadWithLock {

    private Long count=0l;

    public Long run() {
        long start = System.currentTimeMillis();
        for(;count<500_000_000;)
            synchronized (count){
                count++;
            };
        return System.currentTimeMillis()-start;
    }
}
