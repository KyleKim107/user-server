package com.example.userserver.users;

import java.time.ZonedDateTime;

public class UserInfo {
    private final int userId;
    private final String username;
    private final String email;
    private final ZonedDateTime lastPostDateTime;

    public UserInfo(User user) {
        this(user.getUserId(), user.getUsername(), user.getEmail(), user.getLastPostDatetime());
    }

    public UserInfo(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.lastPostDateTime = null;
    }

    public UserInfo(int userId, String username, String email, ZonedDateTime lastPostDateTime) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.lastPostDateTime = lastPostDateTime;
    }


    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public ZonedDateTime getLastPostDateTime() {
        return lastPostDateTime;
    }
}
