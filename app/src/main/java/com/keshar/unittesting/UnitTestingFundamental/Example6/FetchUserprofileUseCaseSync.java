package com.keshar.unittesting.UnitTestingFundamental.Example6;

import com.keshar.unittesting.UnitTestingFundamental.Example5.networking.NetworkErrorException;
import com.keshar.unittesting.UnitTestingFundamental.Example6.networking.UserProfileHttpEndpointSync;
import com.keshar.unittesting.UnitTestingFundamental.Example6.users.User;
import com.keshar.unittesting.UnitTestingFundamental.Example6.users.UserCache;

import static com.keshar.unittesting.UnitTestingFundamental.Example6.networking.UserProfileHttpEndpointSync.*;

public class FetchUserprofileUseCaseSync {
    public enum UseCaseResult {
        SUCCESS,
        FAILURE,
        NETWORK_ERROR
    }

    private final UserProfileHttpEndpointSync mUserProfileHttpEndpointSync;
    private final UserCache mUsersCache;

    public FetchUserprofileUseCaseSync(UserProfileHttpEndpointSync userProfileHttpEndpointSync,
                                       UserCache usersCache) {
        mUserProfileHttpEndpointSync = userProfileHttpEndpointSync;
        mUsersCache = usersCache;
    }

    public UseCaseResult fetchUserProfileSync(String userId) {
        EndpointResult endpointResult;
        try {
            endpointResult = mUserProfileHttpEndpointSync.getUserProfile(userId);
        } catch (NetworkErrorException e) {
            return UseCaseResult.NETWORK_ERROR;
        }

        if (isSuccessfulEndpointResult(endpointResult)) {
            mUsersCache.cacheUser(
                    new User(userId, endpointResult.getFullName(), endpointResult.getImageUrl()));
            return UseCaseResult.SUCCESS;
        } else {
            return UseCaseResult.FAILURE;
        }


    }

    private boolean isSuccessfulEndpointResult(EndpointResult endpointResult) {
        return endpointResult.getStatus() == EndpointResultStatus.SUCCESS;
    }
}
