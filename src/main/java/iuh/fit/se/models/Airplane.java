package iuh.fit.se.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airplane {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String airplaneName;
	
	@OneToMany(mappedBy = "airplane")
	private List<Flight> flights;

}
