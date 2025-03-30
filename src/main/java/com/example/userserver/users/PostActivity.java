package com.example.userserver.users;

import java.time.ZonedDateTime;

public class PostActivity {
    private int userId;
    private ZonedDateTime lastUpdatedDateTime;
    private String lastUpdatedBy;

    public int getUserId() {
        return userId;
    }

    public ZonedDateTime getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
}
