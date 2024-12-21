package iuh.fit.se.services;

import java.util.List;

import iuh.fit.se.models.Flight;

public interface FlightService {


	  public   List<Flight> getAllFlights();
	  public   Flight saveFlight(Flight flight);
	  public   void deleteFlight(Long id);
	
	  public boolean isFlightScheduleConflict(Flight newFlight);
}
