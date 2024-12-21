package iuh.fit.se.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iuh.fit.se.models.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
	public   List<Flight> findAllByOrderByDepartureDateDescAirplaneNameAsc();
	
	public List<Flight> findByAirplaneName(String name);
}
