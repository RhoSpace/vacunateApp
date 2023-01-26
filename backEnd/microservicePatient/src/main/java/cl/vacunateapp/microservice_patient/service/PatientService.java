package cl.vacunateapp.microservice_patient.service;

import cl.vacunateapp.microservice_patient.dto.PatientDto;
import cl.vacunateapp.microservice_patient.entity.Patient;

import java.util.List;

public interface PatientService {
    // Metodo para guardar usuario
    PatientDto savePatient(PatientDto userDto);

    // Metodo para buscar todos los usuarios
    List<PatientDto> findAllPatients();

    // Metodo para buscar usuarios por rut
    Patient findPatientByRut(String rut);

    // Metodo para buscar usuario por id
    Patient findPatientById(Long id);

    // Metodo para borrar usuario
    void deletePatientById(Long id);

    // Metodo para actualizar datos de un usuario
    PatientDto updatePatientData(Long id, PatientDto userDto);
}
