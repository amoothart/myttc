package com.amooth.backupify;

public class Route {
    private final String uri;
    private final String name;

    public Route(String uri, String name) {
        this.uri = uri;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }
}
