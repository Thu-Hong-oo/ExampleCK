package iuh.fit.se.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Repository;

import iuh.fit.se.models.Airplane;

@Repository
public interface AirPlaneRepository extends JpaRepository<Airplane, Long>{

}
