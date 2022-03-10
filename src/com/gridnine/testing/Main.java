package com.gridnine.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> testingFlights = FlightBuilder.createFlights();
        List<Flight> correctFlights = new ArrayList<>(testingFlights);
        for (Flight f: testingFlights) {
            System.out.println(f);
        }
        System.out.println(testingFlights.equals(correctFlights));

        List listOne = Arrays.asList('a', 'b', 'c');
        List listTwo = Arrays.asList('a', 'b', 'c');
        List listThree = Arrays.asList('c', 'a', 'b');

        boolean isEqual = listOne.equals(listTwo);
        System.out.println(isEqual);
    }
}
