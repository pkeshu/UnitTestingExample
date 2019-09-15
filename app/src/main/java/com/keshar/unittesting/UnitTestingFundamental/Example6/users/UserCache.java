package com.keshar.unittesting.UnitTestingFundamental.Example6.users;

import androidx.annotation.Nullable;

public interface UserCache {
    void cacheUser(User user);

    @Nullable
    User getUser(String userId);
}
