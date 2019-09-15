package com.keshar.unittesting.UnitTestingFundamental.Example4;

import com.keshar.unittesting.UnitTestingFundamental.Example3.Interval;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class IntervalAdjacencuDetectorTest {

    IntervalAdjacencuDetector SUT;

    @Before
    public void setUp() throws Exception {
        SUT=new IntervalAdjacencuDetector();
    }

    @Test
    public void isAdjacency_interval1Beforeinterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-11, -3);
        boolean result = SUT.isAdjacency(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacency_interval1Afterinterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(6, 9);
        boolean result = SUT.isAdjacency(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacency_interval1OnStartinterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(3, 9);
        boolean result = SUT.isAdjacency(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacency_interval1OnEndinterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-2, 2);
        boolean result = SUT.isAdjacency(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacency_interval1OnStartAdjacenceinterval2_trueReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(5, 9);
        boolean result = SUT.isAdjacency(interval1, interval2);
        assertThat(result, is(true));
    }

    @Test
    public void isAdjacency_interval1OnEndAdjacenceinterval2_trueReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-5, -1);
        boolean result = SUT.isAdjacency(interval1, interval2);
        assertThat(result, is(true));
    }
}