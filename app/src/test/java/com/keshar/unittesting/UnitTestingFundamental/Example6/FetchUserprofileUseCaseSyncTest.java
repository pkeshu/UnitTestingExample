package com.keshar.unittesting.UnitTestingFundamental.Example6;

import androidx.annotation.Nullable;

import com.keshar.unittesting.UnitTestingFundamental.Example5.networking.NetworkErrorException;
import com.keshar.unittesting.UnitTestingFundamental.Example6.networking.UserProfileHttpEndpointSync;
import com.keshar.unittesting.UnitTestingFundamental.Example6.users.User;
import com.keshar.unittesting.UnitTestingFundamental.Example6.users.UserCache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.keshar.unittesting.UnitTestingFundamental.Example6.FetchUserprofileUseCaseSync.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class FetchUserprofileUseCaseSyncTest {
    public static final String USER_ID = "userId";
    public static final String FULLNAME = "fullname";
    public static final String IMAGEURL = "imageurl";

    UserProfileHttpEndpointSyncTd mUserProfileHttpEndpointSyncTd;
    UserCacheTd mUserCacheTd;

    FetchUserprofileUseCaseSync SUT;

    @Before
    public void setUp() throws Exception {
        mUserProfileHttpEndpointSyncTd = new UserProfileHttpEndpointSyncTd();
        mUserCacheTd = new UserCacheTd();
        SUT = new FetchUserprofileUseCaseSync(mUserProfileHttpEndpointSyncTd, mUserCacheTd);
    }

    @Test
    public void fetchUserProfileSync_success_userIdPassedToEndPoint() throws Exception {
        SUT.fetchUserProfileSync(USER_ID);
        assertThat(mUserProfileHttpEndpointSyncTd.mUserId, is(USER_ID));
    }

    @Test
    public void fetchUserProfileSync_success_userCached() throws Exception {
        SUT.fetchUserProfileSync(USER_ID);
        User userCache = mUserCacheTd.getUser(USER_ID);

        assertThat(userCache.getFullName(), is(FULLNAME));
        assertThat(userCache.getImageUrl(), is(IMAGEURL));
        assertThat(userCache.getUserId(), is(USER_ID));
    }

    @Test
    public void fetchUserProfileSync_generalError_userNotCached() throws Exception {
        mUserProfileHttpEndpointSyncTd.mIsGeneralError = true;
        SUT.fetchUserProfileSync(USER_ID);
        assertThat(mUserCacheTd.getUser(USER_ID), is(nullValue()));
    }

    @Test
    public void fetchUserProfileSync_authError_userNotCached() throws Exception {
        mUserProfileHttpEndpointSyncTd.mIsAuthError = true;
        SUT.fetchUserProfileSync(USER_ID);
        assertThat(mUserCacheTd.getUser(USER_ID), is(nullValue()));
    }

    @Test
    public void fetchUserProfileSync_serverError_userNotCached() throws Exception {
        mUserProfileHttpEndpointSyncTd.mIsServerError = true;
        SUT.fetchUserProfileSync(USER_ID);
        assertThat(mUserCacheTd.getUser(USER_ID), is(nullValue()));
    }

    @Test
    public void fetchUserProfileSync_success_successReturned() throws Exception {
        UseCaseResult result = SUT.fetchUserProfileSync(USER_ID);
        assertThat(result, is(UseCaseResult.SUCCESS));

    }

    @Test
    public void fetchUserProfileSync_serverError_faillureReturned() throws Exception {
        mUserProfileHttpEndpointSyncTd.mIsServerError = true;
        UseCaseResult result = SUT.fetchUserProfileSync(USER_ID);
        assertThat(result, is(UseCaseResult.FAILURE));

    }

    @Test
    public void fetchUserProfileSync_authError_faillureReturned() throws Exception {
        mUserProfileHttpEndpointSyncTd.mIsAuthError = true;
        UseCaseResult result = SUT.fetchUserProfileSync(USER_ID);
        assertThat(result, is(UseCaseResult.FAILURE));

    }

    @Test
    public void fetchUserProfileSync_generalError_faillureReturned() throws Exception {
        mUserProfileHttpEndpointSyncTd.mIsGeneralError = true;
        UseCaseResult result = SUT.fetchUserProfileSync(USER_ID);
        assertThat(result, is(UseCaseResult.FAILURE));

    }

    @Test
    public void fetchUserProfileSync_networkError_networkErrorReturned() throws Exception {
        mUserProfileHttpEndpointSyncTd.mIsNetworkError = true;
        UseCaseResult result = SUT.fetchUserProfileSync(USER_ID);
        assertThat(result, is(UseCaseResult.NETWORK_ERROR));

    }
    //-----------------------------------------------------------------------------------------------------Helper Classes

    public class UserProfileHttpEndpointSyncTd implements UserProfileHttpEndpointSync {
        public String mUserId = "";
        public boolean mIsGeneralError;
        public boolean mIsAuthError;
        public boolean mIsServerError;
        public boolean mIsNetworkError;

        @Override
        public EndpointResult getUserProfile(String userId) throws NetworkErrorException {
            mUserId = userId;
            if (mIsGeneralError) {
                return new EndpointResult(EndpointResultStatus.GENERAL_ERROR, "", "", "");
            } else if (mIsAuthError) {
                return new EndpointResult(EndpointResultStatus.AUTH_ERROR, "", "", "");
            } else if (mIsServerError) {
                return new EndpointResult(EndpointResultStatus.SERVER_ERROR, "", "", "");
            } else if (mIsNetworkError) {
                throw new NetworkErrorException();
            } else {
                return new EndpointResult(EndpointResultStatus.SUCCESS, USER_ID, FULLNAME, IMAGEURL);
            }

        }
    }

    public class UserCacheTd implements UserCache {

        List<User> mUsers = new ArrayList<>(1);

        @Override
        public void cacheUser(User user) {

            User existingUser = getUser(user.getUserId());
            if (existingUser != null) {
                mUsers.remove(existingUser);
            }
            mUsers.add(user);
        }

        @Nullable
        @Override
        public User getUser(String userId) {
            for (User user : mUsers) {
                if (user.getUserId().equals(userId)) {
                    return user;
                }
            }
            return null;
        }
    }
}