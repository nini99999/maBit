package com.poshist.maBit.vo;

import org.apache.http.NameValuePair;

/**
 * Created by poshist on 18-1-27.
 */
public class PostValue implements NameValuePair {
    private String name;
    private String value;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
