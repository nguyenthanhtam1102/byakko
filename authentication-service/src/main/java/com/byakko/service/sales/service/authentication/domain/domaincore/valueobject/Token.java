package com.byakko.service.sales.service.authentication.domain.domaincore.valueobject;

import com.byakko.service.sales.common.DomainConstants;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Token {

    private String userId;
    private ZonedDateTime createdAt;
    private ZonedDateTime expiredTime;
    private String randomData;
    private String hashedData;
    private boolean used;

    public void validate() {
        if(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).compareTo(expiredTime) > 0)
            throw new RuntimeException("Token da het han");
        if(used)
            throw new RuntimeException("Token da duoc su dung");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(ZonedDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getRandomData() {
        return randomData;
    }

    public void setRandomData(String randomData) {
        this.randomData = randomData;
    }

    public String getHashedData() {
        return hashedData;
    }

    public void setHashedData(String hashedData) {
        this.hashedData = hashedData;
    }

    public boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "Token{" +
                "userId='" + userId + '\'' +
                ", createdAt=" + createdAt +
                ", expiredTime=" + expiredTime +
                ", randomData='" + randomData + '\'' +
                ", hashedData='" + hashedData + '\'' +
                ", used=" + used +
                '}';
    }

    public static final class Builder {
        private String userId;
        private ZonedDateTime createdAt;
        private ZonedDateTime expiredTime;
        private String randomData;
        private String hashedData;
        private boolean used;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder createdAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder expiredTime(ZonedDateTime expiredTime) {
            this.expiredTime = expiredTime;
            return this;
        }

        public Builder randomData(String randomData) {
            this.randomData = randomData;
            return this;
        }

        public Builder hashedData(String hashedData) {
            this.hashedData = hashedData;
            return this;
        }

        public Builder used(boolean used) {
            this.used = used;
            return this;
        }

        public Token build() {
            Token token = new Token();
            token.setUserId(userId);
            token.setCreatedAt(createdAt);
            token.setExpiredTime(expiredTime);
            token.setRandomData(randomData);
            token.setHashedData(hashedData);
            token.setUsed(used);
            return token;
        }
    }
}
