package com.keshar.unittesting.UnitTestingFundamental.Example5.networking;

public interface LoginHttpEndPointSync {
    EndpointResult loginSync(String username, String password) throws NetworkErrorException;

    enum EndpointResultStatus {
        SUCCESS,
        AUTH_ERROR,
        SERVER_ERROR,
        GENERAL_ERROR
    }

    class EndpointResult {
        private final EndpointResultStatus mStatus;
        private final String mAuthToken;

        public EndpointResult(EndpointResultStatus status, String authToken) {
            mStatus = status;
            mAuthToken = authToken;
        }

        public EndpointResultStatus getStatus() {
            return mStatus;
        }

        public String getAuthToken() {
            return mAuthToken;
        }
    }
}
