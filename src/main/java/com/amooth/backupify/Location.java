package com.amooth.backupify;

import java.util.List;

public class Location {

    private List<Stop> stops;

    public Location(List<Stop> stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "Location{" +
                "stops=" + stops +
                '}';
    }

    public List<Stop> getStops() {
        return stops;
    }
}
