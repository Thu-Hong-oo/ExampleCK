package iuh.fit.se.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Airplane name is required")
    private String airplaneName;

    @NotBlank(message = "Departure airport is required")
    private String departureAirport;

    @NotBlank(message = "Arrival airport is required")
    private String arrivalAirport;

    @Future(message = "Departure date must be in the future")
    @Temporal(TemporalType.DATE)
    private LocalDate departureDate;

    @Future(message = "Arrival date must be in the future")
    @Temporal(TemporalType.DATE)
    private LocalDate arrivalDate;

    @Min(value = 0, message = "Seat fare must be greater than 0")
    private double seatFare;

    @Enumerated(EnumType.STRING)  
    private FlightStatus flightStatus;

    
    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;
}

