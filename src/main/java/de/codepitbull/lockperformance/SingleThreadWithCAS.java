package de.codepitbull.lockperformance;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jochen Mader
 */
public class SingleThreadWithCAS {
    public Long run() {
        AtomicLong count=new AtomicLong();
        long start = System.currentTimeMillis();
        while(count.incrementAndGet()!=500_000_000);
        return System.currentTimeMillis()-start;
    }
}
