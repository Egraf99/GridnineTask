package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> testingFlights = FlightBuilder.createFlights();
        for (Flight f: testingFlights) {
            System.out.println(f);
        }

        List<Flight> correctFLight = FlightFilter.byFirstDepAfterNow(testingFlights);

//        for (Flight f: correctFLight) {
//            System.out.println(f);
//        }
        System.out.println();
        correctFLight = FlightFilter.by(testingFlights, "firstDepAfterNow", "sdfsfafsdaf");

        for (Flight f: correctFLight) {
            System.out.println(f);
        }

//        System.out.println();
//        correctFLight = FlightFilter.byArrAfterDep(testingFlights);
//
//        for (Flight f: correctFLight)
//            System.out.println(f);
//
//        System.out.println();
//        correctFLight = FlightFilter.byTimeOnGroundLessTwoHours(testingFlights);
//
//        for (Flight f: correctFLight)
//            System.out.println(f);
//
//        System.out.println();
//        correctFLight = FlightFilter.byArrAfterDep(FlightFilter.byTimeOnGroundLessTwoHours(testingFlights));
//
//        for (Flight f: correctFLight)
//            System.out.println(f);
//        System.out.println();
//
//
//        correctFLight = FlightFilter.byTimeOnGroundLessTwoHours(testingFlights);
//
//        for (Flight f: correctFLight)
//            System.out.println(f);
    }
}
