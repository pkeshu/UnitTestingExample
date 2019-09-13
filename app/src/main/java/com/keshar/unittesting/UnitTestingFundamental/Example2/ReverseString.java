package com.keshar.unittesting.UnitTestingFundamental.Example2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ReverseString {
    public String reverse(@Nullable String string) {
        if(string==null){
            return "";
        }
        StringBuilder s = new StringBuilder();

        for (int i = string.length() - 1; i >= 0; i--) {
            s.append(string.charAt(i));
        }
        return s.toString();
    }
}
