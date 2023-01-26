package cl.vacunateapp.microservice_patient.repository;

import cl.vacunateapp.microservice_patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Buscar por rut
    Optional<Patient> findPatientByRut(String rut);

    // Buscar por id
    Optional<Patient> findPatientById(Long id);
}
