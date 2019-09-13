package com.keshar.unittesting.UnitTestingFundamental.excersize2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NegativeNumberValidatorTest {

    NegativeNumberValidator SUT;

    @Before
    public void setUp() {
        SUT = new NegativeNumberValidator();
    }

    @Test
    public void isNegative_NegativeValue_ReturnTrue() {
        boolean result = SUT.isNgeative(-1);
        assertThat(result, is(true));
    }

    @Test
    public void isNegative_PositiveValue_ReturnFalse() {
        boolean result = SUT.isNgeative(1);
        assertThat(result, is(false));
    }

    @Test
    public void isNegative_ZeroValue_ReturnFalse() {
        boolean result = SUT.isNgeative(0);
        assertThat(result, is(false));
    }

}