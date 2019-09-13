package com.keshar.unittesting.Example1;

import com.keshar.unittesting.UnitTestingFundamental.excersize1.PositiveNumberValidator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PositiveNumberValidatorTest {

    PositiveNumberValidator SUT;

    @Before
    public void setUp() {
        SUT = new PositiveNumberValidator();
    }

    @Test
    public void test1() {
        boolean result = SUT.isPositive(-1);
        assertThat(result, is(false));
    }

    @Test
    public void test2() {
        boolean result = SUT.isPositive(0);
        assertThat(result, is(false));
    }

    @Test
    public void test3() {
        boolean result = SUT.isPositive(1);
        assertThat(result, is(true));
    }

}