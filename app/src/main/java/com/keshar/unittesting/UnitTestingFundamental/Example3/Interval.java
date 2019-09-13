package com.keshar.unittesting.UnitTestingFundamental.Example3;

public class Interval {
    private final int mStart;
    private final int mEnd;

    public Interval(int mStart, int mEnd) {
        if (mStart >= mEnd) {
            throw new IllegalArgumentException("Invalid Interval Range");
        }
        this.mStart = mStart;
        this.mEnd = mEnd;
    }

    public int getmStart() {
        return mStart;
    }

    public int getmEnd() {
        return mEnd;
    }
}
