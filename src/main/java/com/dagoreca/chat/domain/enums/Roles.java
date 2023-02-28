package com.dagoreca.chat.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Roles {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");

    private String value;
    Roles(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
