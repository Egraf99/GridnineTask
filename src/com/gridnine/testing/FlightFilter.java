package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of filtering methods
 */
public class FlightFilter {

    /**
     * Return Flights where any departed time of Segment after now time
     */
    static List<Flight> byAnyDepAfterNow(List<Flight> flights) {
        final List<Flight> correctFlights = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Flight flight : flights) {
            boolean correct = true;  // check that all Segments in one Flight passed test
            for (Segment segment : flight.getSegments()) {
                if (correct && segment.getDepartureDate().isBefore(now))
                    correct = false;
            }
            if (correct) correctFlights.add(flight);
        }
        return correctFlights;
    }

    /**
     * Return Flights where departed time of first Segments after now time
     */
    static List<Flight> byFirstDepAfterNow(List<Flight> flights) {
        final List<Flight> correctFlights = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Flight flight : flights) {
            if (!flight.getSegments().get(0).getDepartureDate().isBefore(now)) // departure time of first segment not before now
                correctFlights.add(flight);
        }

        return correctFlights;
    }

    /**
     * Return Flights where in Segments all arriving time after department time
     */
    static List<Flight> byArrAfterDep(List<Flight> flights) {
        final List<Flight> correctFlights = new ArrayList<>();
        for (Flight flight : flights) {

            boolean correct = true;  // check that all Segments in one Flight passed test
            for (Segment segment : flight.getSegments()) {
                if (correct && segment.getDepartureDate().isAfter(segment.getArrivalDate()))
                    correct = false;
            }
            if (correct) correctFlights.add(flight);
        }
        return correctFlights;
    }

    /**
     * Return Flight where time on ground between Segments more than two hours
     */
    static List<Flight> byTimeOnGroundMoreTwoHours(List<Flight> flights) {
        final List<Flight> correctFlights = new ArrayList<>();
        long sumHours = 0;
        for (Flight flight : flights) {
            LocalDateTime prevArrival = null;
            for (Segment segment : flight.getSegments()) {
                if (prevArrival == null) {
                    prevArrival = segment.getArrivalDate();
                    continue;
                }

                sumHours = ChronoUnit.HOURS.between(prevArrival, segment.getDepartureDate());
            }
            if (sumHours < 2) correctFlights.add(flight);
        }
        return correctFlights;
    }

}