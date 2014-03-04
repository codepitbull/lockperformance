package de.codepitbull.lockperformance;

import java.util.concurrent.Callable;

/**
 * @author Jochen Mader
 */
public class SingleThread {

    private Long count=0l;

    public Long run() {
        long start = System.currentTimeMillis();
        for(;count<500_000_000;count++);
        return System.currentTimeMillis()-start;
    }
}
