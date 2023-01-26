package cl.vacunateapp.microservice_patient.service;

import cl.vacunateapp.microservice_patient.dto.PatientDto;
import cl.vacunateapp.microservice_patient.entity.Patient;
import cl.vacunateapp.microservice_patient.exception.badrequest.id.IdNotRegisteredException;
import cl.vacunateapp.microservice_patient.exception.badrequest.rut.RutNotRegisteredException;
import cl.vacunateapp.microservice_patient.exception.conflict.rut.RutRegisteredException;
import cl.vacunateapp.microservice_patient.repository.PatientRepository;
import cl.vacunateapp.microservice_patient.utils.DtoUtils;
import cl.vacunateapp.microservice_patient.utils.IdUtils;
import cl.vacunateapp.microservice_patient.utils.RutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;

    // Metodo para guardar usuario
    @Override
    public PatientDto savePatient(PatientDto userDto) {
        //Compruebo si el rut ya esta registrado
        checkRutRegistered(userDto);

        Patient patientToSave = Patient.builder()
                .rut(RutUtils.checkValidRut(userDto.getRut()))
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .creationDate(LocalDateTime.now())
                .build();
        patientRepository.save(patientToSave);

        return PatientDto.builder()
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

    // Metodo para buscar todos los usuarios
    @Override
    public List<PatientDto> findAllPatients() {
        List<Patient> patientList = patientRepository.findAll();

        return patientList.stream()
                .map(user -> PatientDto.builder()
                        .rut(user.getRut())
                        .name(user.getName())
                        .lastName(user.getLastName())
                        .phone(user.getPhone())
                        .email(user.getEmail())
                        .build())
                .toList();
    }

    // Metodo para buscar usuarios por rut
    @Override
    public Patient findPatientByRut(String rut) {
        DtoUtils.checkEmptyNullRut(rut);
        RutUtils.checkValidRut(rut);

        return patientRepository.findPatientByRut(rut).orElseThrow(() -> new RutNotRegisteredException(rut));
    }

    // Metodo para buscar usuario por id
    @Override
    public Patient findPatientById(Long id) {
        IdUtils.checkId(id);
        return patientRepository.findPatientById(id)
                .orElseThrow(() -> new IdNotRegisteredException(id.toString()));
    }

    // Metodo para borrar usuario
    @Override
    public void deletePatientById(Long id) {
        Patient patientToDelete = findPatientById(id);
        patientRepository.delete(patientToDelete);
    }

    // Metodo para actualizar datos de un usuario
    @Override
    public PatientDto updatePatientData(Long id, PatientDto userDto) {
        //Cargo al usuario que se intenta actualizar
        Patient patientNoUpdate = findPatientById(id);

        //compruebo que datos se van a actualizar
        Optional.ofNullable(userDto.getName()).ifPresent(patientNoUpdate::setName);
        Optional.ofNullable(userDto.getLastName()).ifPresent(patientNoUpdate::setLastName);
        Optional.ofNullable(userDto.getPhone()).ifPresent(patientNoUpdate::setPhone);
        Optional.ofNullable(userDto.getEmail()).ifPresent(patientNoUpdate::setEmail);

        patientRepository.save(patientNoUpdate);

        return PatientDto.builder()
                .rut(patientNoUpdate.getRut())
                .name(patientNoUpdate.getName())
                .lastName(patientNoUpdate.getLastName())
                .phone(patientNoUpdate.getPhone())
                .email(patientNoUpdate.getEmail())
                .build();
    }

    // Metodos auxiliares
    public void checkRutRegistered(PatientDto userDto) {
        if (patientRepository.findPatientByRut(userDto.getRut()).isPresent()) {
            throw new RutRegisteredException(userDto.getRut());
        }
    }
}
