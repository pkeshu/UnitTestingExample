package com.keshar.unittesting.UnitTestingFundamental.Example4;

import com.keshar.unittesting.UnitTestingFundamental.Example3.Interval;

public class IntervalAdjacencuDetector {
    public boolean isAdjacency(Interval interval1, Interval interval2) {
        return interval1.getmStart() == interval2.getmEnd() || interval1.getmEnd() == interval2.getmStart();
    }
}
