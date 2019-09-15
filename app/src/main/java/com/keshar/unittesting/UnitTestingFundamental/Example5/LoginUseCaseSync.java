package com.keshar.unittesting.UnitTestingFundamental.Example5;

import com.keshar.unittesting.UnitTestingFundamental.Example5.authtoken.AuthTokenCache;
import com.keshar.unittesting.UnitTestingFundamental.Example5.eventbus.EventBusPoster;
import com.keshar.unittesting.UnitTestingFundamental.Example5.eventbus.LoggedInEvent;
import com.keshar.unittesting.UnitTestingFundamental.Example5.networking.LoginHttpEndPointSync;
import com.keshar.unittesting.UnitTestingFundamental.Example5.networking.NetworkErrorException;

public class LoginUseCaseSync {
    public enum UseCaseResult {
        SUCCESS,
        FAILURE,
        NETWORK_ERROR
    }

    private final LoginHttpEndPointSync mLoginHttpEndpointSync;
    private final AuthTokenCache mAuthTokenCache;
    private final EventBusPoster mEventBusPoster;

    public LoginUseCaseSync(LoginHttpEndPointSync loginHttpEndpointSync,
                            AuthTokenCache authTokenCache,
                            EventBusPoster eventBusPoster) {
        mLoginHttpEndpointSync = loginHttpEndpointSync;
        mAuthTokenCache = authTokenCache;
        mEventBusPoster = eventBusPoster;
    }

    public UseCaseResult loginSync(String username, String password) {
        LoginHttpEndPointSync.EndpointResult endpointEndpointResult;
        try {
            endpointEndpointResult = mLoginHttpEndpointSync.loginSync(username, password);
        } catch (NetworkErrorException e) {
            return UseCaseResult.NETWORK_ERROR;
        }


        if (isSuccessfulEndpointResult(endpointEndpointResult)) {
            mAuthTokenCache.cacheAuthToken(endpointEndpointResult.getAuthToken());
            mEventBusPoster.postEvent(new LoggedInEvent());
            return UseCaseResult.SUCCESS;
        } else {
            return UseCaseResult.FAILURE;
        }
    }

    private boolean isSuccessfulEndpointResult(LoginHttpEndPointSync.EndpointResult endpointResult) {
        return endpointResult.getStatus() == LoginHttpEndPointSync.EndpointResultStatus.SUCCESS;
    }
}
