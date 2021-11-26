package patients.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import patients.dto.PatientDto;
import patients.mapper.PatientMapper;
import patients.model.Patient;
import patients.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientService {

    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    public Patient save(Patient patient) { return patientRepository.save(patient); }

    public Patient save(PatientDto patientDto) { return save(patientMapper.toEntity(patientDto)); }

    public List<Patient> findAll() { return patientRepository.findAll(); }

    public Optional<Patient> findById(String id) { return patientRepository.findById(id); }

    public Patient update(Patient patientDetails, Patient patientToUpdate) {
        patientToUpdate.setLastName(patientDetails.getLastName());
        patientToUpdate.setFirstName(patientDetails.getFirstName());
        patientToUpdate.setAddress(patientDetails.getAddress());
        patientToUpdate.setSex(patientDetails.getSex());
        patientToUpdate.setBirthdate(patientDetails.getBirthdate());
        patientToUpdate.setPhoneNumber(patientDetails.getPhoneNumber());

        return patientRepository.save(patientToUpdate);
    }

    public Patient update(PatientDto patientDetails, Patient patientToUpdate) {
        return update(patientMapper.toEntity(patientDetails), patientToUpdate);
    }

    public void delete(Patient patient) { patientRepository.delete(patient); }

}
