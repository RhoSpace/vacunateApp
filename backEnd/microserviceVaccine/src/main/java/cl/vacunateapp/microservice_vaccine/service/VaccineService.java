package cl.vacunateapp.microservice_vaccine.service;

import cl.vacunateapp.microservice_vaccine.dto.VaccineDto;
import cl.vacunateapp.microservice_vaccine.entity.Vaccine;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VaccineService {
    VaccineDto createVaccine(VaccineDto vaccineDto);

    List<Vaccine> vaccineList();
    @Transactional
    VaccineDto updateVaccineCount(Long id, VaccineDto vaccineDto);

    @Transactional
    VaccineDto updateVaccineNumberWhenAPatientIsVaccinated(Long id);

    // Metodo para buscar vacuna por id
    Vaccine findVaccineById(Long id);
}
