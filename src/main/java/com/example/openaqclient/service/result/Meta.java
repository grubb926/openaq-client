package com.example.openaqclient.service.result;

public class Meta {

    private String name;
    private String license;
    private String website;
    private String page;
    private int limit;
    private int found;

    public Meta(String name, String license, String website, String page, int limit, int found) {
        this.name = name;
        this.license = license;
        this.website = website;
        this.page = page;
        this.limit = limit;
        this.found = found;
    }

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }

    public String getWebsite() {
        return website;
    }

    public String getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }

    public int getFound() {
        return found;
    }
}
