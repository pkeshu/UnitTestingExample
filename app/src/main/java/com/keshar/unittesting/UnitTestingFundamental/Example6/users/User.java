package com.keshar.unittesting.UnitTestingFundamental.Example6.users;

public class User {
    private final String userId;
    private final String fullName;
    private final String imageUrl;

    public User(String userId, String fullName, String imageUrl) {
        this.userId = userId;
        this.fullName = fullName;
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
