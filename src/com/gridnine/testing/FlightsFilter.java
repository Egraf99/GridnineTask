package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class of filtering methods
 */
public class FlightsFilter {

    /**
     * Filter flights by given condition and return only passed flights
     *
     * @param flights    List of flights
     * @param conditions Strings of filtering conditions
     * @return Correct flights
     */
    static List<Flight> by(List<Flight> flights, String... conditions) {
        List<String> exitingCond = List.of("anyDepAfterNow", "firstDepAfterNow", "arrAfterDep", "timeOnGroundLessTwoHours");
        final List<Flight> correctFlights = new ArrayList<>();

        for (Flight flight : flights) {
            boolean correct = false;

            for (String cond : conditions) {
                if (Objects.equals(cond, "firstDepAfterNow")) {
                    correct = byFirstDepAfterNow(flight.getSegments());
                } else if (Objects.equals(cond, "anyDepAfterNow")) {
                    correct = byAnyDepAfterNow(flight.getSegments());
                } else if (Objects.equals(cond, "arrAfterDep")) {
                    correct = byArrAfterDep(flight.getSegments());
                } else if (Objects.equals(cond, "timeOnGroundLessTwoHours")) {
                    correct = byTimeOnGroundLessTwoHours(flight.getSegments());
                } else
                    throw new IllegalArgumentException("Incorrect condition: " + cond + "\nPermissible conditions: " + exitingCond);

                if (!correct) break; //don't check other conditions
            }
            if (correct) correctFlights.add(flight);
        }
        return correctFlights;

    }

    /**
     * Return boolean - any departed time of Segment after now time
     */
    private static boolean byAnyDepAfterNow(List<Segment> segments) {
        LocalDateTime now = LocalDateTime.now();
        boolean correct = true;  // check that all Segments in one Flight passed test
        for (Segment segment : segments) {
            if (correct && segment.getDepartureDate().isBefore(now)) correct = false;
        }
        return correct;
    }

    /**
     * Return boolean - departed time of first Segments after now time
     */
    private static boolean byFirstDepAfterNow(List<Segment> segments) {
        LocalDateTime now = LocalDateTime.now();
        return (!segments.get(0).getDepartureDate().isBefore(now));
    }

    /**
     * Return boolean - in Segments all arriving time after department time
     */
    private static boolean byArrAfterDep(List<Segment> segments) {
        boolean correct = true;
        for (Segment segment : segments) {
            if (correct && segment.getDepartureDate().isAfter(segment.getArrivalDate())) correct = false;
        }
        return correct;
    }

    /**
     * Return boolean - time on ground between Segments more than two hours
     */
    private static boolean byTimeOnGroundLessTwoHours(List<Segment> segments) {
        long sumHours = 0;
        LocalDateTime prevArrival = null;
        for (Segment segment : segments) {
            if (prevArrival == null) {
                prevArrival = segment.getArrivalDate();
                continue;
            }

            sumHours = ChronoUnit.HOURS.between(prevArrival, segment.getDepartureDate());
        }
        return sumHours < 2;
    }

}