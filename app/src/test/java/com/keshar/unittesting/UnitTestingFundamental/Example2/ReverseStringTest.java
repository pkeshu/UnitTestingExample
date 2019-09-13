package com.keshar.unittesting.UnitTestingFundamental.Example2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReverseStringTest {
    ReverseString SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new ReverseString();
    }

    @Test
    public void reverse_emptyString_emptyStringReturned() throws Exception {

        String result = SUT.reverse("");
        assertThat(result, is(""));
    }
    @Test
    public void reverse_nullString_emptyStringReturned() throws Exception {

        String result = SUT.reverse(null);
        assertThat(result, is(""));
    }

    @Test
    public void revserse_singleCharecter_sameCharecterReturned() throws Exception {
        String result = SUT.reverse("a");
        assertThat(result, is("a"));
    }

    @Test
    public void reverse_validString_reversedStringReturned() throws Exception {
        String result = SUT.reverse("Keshar Paudel");
        assertThat(result, is("leduaP rahseK"));
    }
}