package iuh.fit.se.serviceIml;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iuh.fit.se.models.Flight;
import iuh.fit.se.repositories.AirPlaneRepository;
import iuh.fit.se.repositories.FlightRepository;
import iuh.fit.se.services.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private AirPlaneRepository airPlaneRepository;
	@Override
	public List<Flight> getAllFlights() {
		return flightRepository.findAllByOrderByDepartureDateDescAirplaneNameAsc();
	}

	@Override
	public Flight saveFlight(Flight flight) {
		if(flight.getAirplaneName()=="")
            throw new IllegalArgumentException("AirPlaneName is not null");
		if(flight.getDepartureAirport()=="")
            throw new IllegalArgumentException("DepartureAirport is not null");
		if(flight.getArrivalAirport()=="")
            throw new IllegalArgumentException("ArrivalAirport is not null");
		if(flight.getSeatFare() <=0)
            throw new IllegalArgumentException("SeatFare must be greater 0");

		  if (flight.getDepartureDate().isBefore(LocalDate.now()) || flight.getArrivalDate().isBefore(LocalDate.now())) {
	            throw new IllegalArgumentException("Departure and Arrival date must be in the future");
	        }

	        if (flight.getSeatFare() <= 0) {
	            throw new IllegalArgumentException("Seat fare must be greater than 0");
	        }

	        if (isFlightScheduleConflict(flight)) {
	            throw new IllegalArgumentException("Flight schedule conflicts with existing flights");
	        }

	      return  flightRepository.save(flight);
	}

	@Override
	public void deleteFlight(Long id) {
		// TODO Auto-generated method stub
		flightRepository.deleteById(id);
	}

	@Override
	   public boolean isFlightScheduleConflict(Flight newFlight) {
        // Lấy danh sách tất cả các chuyến bay của cùng một máy bay
        List<Flight> existingFlights = flightRepository.findByAirplaneName(newFlight.getAirplaneName());
        
        // Kiểm tra xem chuyến bay mới có trùng lịch không
        for (Flight existingFlight : existingFlights) {
            // Kiểm tra nếu chuyến bay mới trùng với các chuyến bay đã có
            if ((newFlight.getDepartureDate().isBefore(existingFlight.getArrivalDate()) || 
                 newFlight.getDepartureDate().isEqual(existingFlight.getArrivalDate())) &&
                (newFlight.getArrivalDate().isAfter(existingFlight.getDepartureDate()) || 
                 newFlight.getArrivalDate().isEqual(existingFlight.getDepartureDate()))) {
                return true; // Trùng lịch
            }
        }
        return false; // Không trùng lịch
    }

}
