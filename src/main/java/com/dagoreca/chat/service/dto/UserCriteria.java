package com.dagoreca.chat.service.dto;

import java.io.Serializable;


public class UserCriteria implements Serializable {

    private String nameStartsWith;

    public UserCriteria(){
    }

    public String getNameStartsWith() {
        return nameStartsWith;
    }

    public void setNameStartsWith(String nameStartsWith) {
        this.nameStartsWith = nameStartsWith;
    }

    @Override
    public String toString() {
        return "UserCriteria{" +
                "nameStartsWith='" + nameStartsWith + '\'' +
                '}';
    }
}
