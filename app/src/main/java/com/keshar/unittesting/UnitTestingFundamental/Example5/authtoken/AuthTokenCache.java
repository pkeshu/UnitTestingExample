package com.keshar.unittesting.UnitTestingFundamental.Example5.authtoken;

public interface AuthTokenCache {
    void cacheAuthToken(String authToken);

    String getAuthToken();
}
