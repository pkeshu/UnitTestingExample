package com.keshar.unittesting.UnitTestingFundamental.Example3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class IntervalOverlapsDetectorTest {

    IntervalOverlapsDetector SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new IntervalOverlapsDetector();
    }

    @Test
    public void isOverlap_interval1Beforeinterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-11, -3);
        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isOverlap_interval1Afterinterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(6, 9);
        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isOverlap_interval1OnStartinterval2_trueReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(3, 9);
        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(true));
    }

    @Test
    public void isOverlap_interval1OnEndinterval2_trueReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-2, 2);
        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(true));
    }

    @Test
    public void isOverlap_interval1OnStartAdjacenceinterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(5, 9);
        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isOverlap_interval1OnEndAdjacenceinterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-5, -1);
        boolean result = SUT.isOverlap(interval1, interval2);
        assertThat(result, is(false));
    }
}