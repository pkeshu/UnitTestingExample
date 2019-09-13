package com.keshar.unittesting.UnitTestingFundamental.Example3;

public class IntervalOverlapsDetector {
    public boolean isOverlap(Interval interval1, Interval interval2) {
        return interval1.getmEnd() > interval2.getmStart() && interval1.getmStart() < interval2.getmEnd();
    }
}
