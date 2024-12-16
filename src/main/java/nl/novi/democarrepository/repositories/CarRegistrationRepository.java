package nl.novi.democarrepository.repositories;

import nl.novi.democarrepository.models.CarRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRegistrationRepository extends JpaRepository<CarRegistration, Long> {
    Optional<CarRegistration> findByIdAndCarId(Long id, Long carId);
}
