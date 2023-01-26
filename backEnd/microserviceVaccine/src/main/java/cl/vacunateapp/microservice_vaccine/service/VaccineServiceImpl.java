package cl.vacunateapp.microservice_vaccine.service;

import cl.vacunateapp.microservice_vaccine.dto.VaccineDto;
import cl.vacunateapp.microservice_vaccine.entity.Vaccine;
import cl.vacunateapp.microservice_vaccine.exception.badrequest.id.IdNotRegisteredException;
import cl.vacunateapp.microservice_vaccine.repository.VaccineRepository;
import cl.vacunateapp.microservice_vaccine.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService{

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public VaccineDto createVaccine(VaccineDto vaccineDto) {
        Vaccine vaccineToSave = Vaccine.builder()
                .name(vaccineDto.getName())
                .amount(0)
                .build();
        vaccineRepository.save(vaccineToSave);

        return VaccineDto.builder()
                .name(vaccineToSave.getName())
                .amount(vaccineToSave.getAmount())
                .build();
    }

    @Override
    public List<Vaccine> vaccineList() {
        return vaccineRepository.findAll();
    }

    @Override
    @Transactional
    public VaccineDto updateVaccineCount(Long id, VaccineDto vaccineDto) {
        Vaccine actualVaccine = findVaccineById(id);
        vaccineRepository.updateVaccineCount(actualVaccine.getName(), actualVaccine.getAmount() + vaccineDto.getAmount());
        return VaccineDto.builder()
                .name(actualVaccine.getName())
                .amount(actualVaccine.getAmount() + vaccineDto.getAmount())
                .build();
    }

    @Override
    @Transactional
    public VaccineDto updateVaccineNumberWhenAPatientIsVaccinated(Long id) {
        /*
        comprobar que el paciente realmente se vacuno
        restar una vacuna
         */

        //Restar una vacuna
        Vaccine actualVaccine = findVaccineById(id);
        vaccineRepository.updateVaccineCount(actualVaccine.getName(), actualVaccine.getAmount() - 1);
        return VaccineDto.builder()
                .name(actualVaccine.getName())
                .amount(actualVaccine.getAmount() - 1)
                .build();
    }

    // Metodo para buscar vacuna por id
    @Override
    public Vaccine findVaccineById(Long id) {
        IdUtils.checkId(id);
        return vaccineRepository.findVaccineById(id)
                .orElseThrow(() -> new IdNotRegisteredException(id.toString()));
    }
}
