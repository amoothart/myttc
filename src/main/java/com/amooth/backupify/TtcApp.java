package com.amooth.backupify;

import java.io.IOException;
import java.net.URISyntaxException;

public class TtcApp {

    private static TtcClient client;

    public static void main(String[] args) throws IOException, URISyntaxException {
        client = new TtcClient();

        if(args.length < 1) {
            System.out.println("Usage: specify a location and an optional stop name. Eg. \"finch_station\" \"Finch Station Bus Bay\"");
        } else if (args.length == 1) {
            stopsForLocation(args[0]);
        } else {
            routesForStop(args[0], args[1]);
        }
    }

    private static void stopsForLocation(String location) throws IOException, URISyntaxException {
        System.out.println(client.getLocation(location).toString());
    }

    private static void routesForStop(String locationUri, String stopName) throws IOException, URISyntaxException {
        Location location = client.getLocation(locationUri);
        for(Stop stop : location.getStops()) {
            if(stop.getName().equals(stopName)) {
                System.out.println(stop.getRoutes().toString());
                return;
            }
        }
        System.out.println("No matching stops found");
    }
}
