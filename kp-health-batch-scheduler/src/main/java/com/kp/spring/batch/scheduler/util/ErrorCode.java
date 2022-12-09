package com.kp.spring.batch.scheduler.util;

public enum ErrorCode {
    POLICY_HOLDER_NON_EXISTENCE("E0001", "Policy Holder Id does not exists."),
    POLICY_ID_NON_EXISTENCE("E0002", "Policy Id does not exists.");

    private final String code;
    private final String description;

    private ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
