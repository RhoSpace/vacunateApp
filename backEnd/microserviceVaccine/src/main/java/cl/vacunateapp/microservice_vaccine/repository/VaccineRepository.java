package cl.vacunateapp.microservice_vaccine.repository;

import cl.vacunateapp.microservice_vaccine.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    Optional<Vaccine> findVaccineById(Long id);

    @Modifying
    @Query("update Vaccine set amount=:amount where name=:name")
    void updateVaccineCount(@Param("name") String name, @Param("amount") Integer amount);
}
