package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> testingFlights = FlightBuilder.createFlights();

        // first: filter flights where department before now
        List<Flight> correctFLight = FlightsFilter.by(testingFlights, "firstDepAfterNow");
        System.out.println(correctFLight);

        //second: filter flights where arrival before department
        correctFLight = FlightsFilter.by(testingFlights, "arrAfterDep");
        System.out.println(correctFLight);

        //third: filter flights where time on ground more 2 hours
        correctFLight = FlightsFilter.by(testingFlights, "timeOnGroundLessTwoHours");
        System.out.println(correctFLight);
    }
}
