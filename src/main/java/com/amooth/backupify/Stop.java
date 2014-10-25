package com.amooth.backupify;

import java.util.List;

public class Stop {

    private final String name;
    private final String uri;
    private final List<Route> routes;

    public Stop(String name, String uri, List<Route> routes) {
        this.name = name;
        this.uri = uri;
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                ", routes=" + routes +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
