package com.gridnine.testing;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFlights extends Assert {
   List<Flight> testingFlights;
   List<Flight> correctFlights;
   List<Flight> receivedFlights;

    @Before
    public void setUp() {
      testingFlights = new ArrayList<>(FlightBuilder.createFlights());
      correctFlights = new ArrayList<>(testingFlights);

    }

    @After
   public void tearDown() {
       testingFlights = null;
    }

    @Test
   public void filterFlightsWithDepartedBeforeNow() {
        correctFlights.remove(2);
        receivedFlights = FlightFilter.by(testingFlights, "firstDepAfterNow");
        assertEquals(correctFlights, receivedFlights);
    }

    @Test
    public void filterFlightsWithAllDepartedSegmentsAfterNow() {
        correctFlights.remove(2);
        receivedFlights = FlightFilter.by(testingFlights, "anyDepAfterNow");
        assertEquals(correctFlights, receivedFlights);
    }

    @Test
    public void filterFlightsWithArrAfterDep() {
        correctFlights.remove(3);
        receivedFlights = FlightFilter.by(testingFlights, "arrAfterDep");
        assertEquals(correctFlights, receivedFlights);
    }

    @Test
    public void filterFlightsWithTimeOnGroundLessTwoHour() {
        correctFlights.remove(5);
        correctFlights.remove(4);
        receivedFlights = FlightFilter.by(testingFlights, "timeOnGroundLessTwoHours");
        assertEquals(correctFlights, receivedFlights);
    }

    @Test
    public void filterFlightsWithTimeOnGroundLessTwoHourAndArrAfterDep() {
        // several filter at the same time
        correctFlights.subList(3, 6).clear();
        receivedFlights = FlightFilter.by(testingFlights, "timeOnGroundLessTwoHours", "arrAfterDep");
        assertEquals(correctFlights, receivedFlights);
    }
}
