package patients.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import patients.model.Patient;
import patients.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientService {

    private PatientRepository patientRepository;

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> findAll() { return patientRepository.findAll(); }

    public Optional<Patient> findById(long id) { return patientRepository.findById(id); }

    public Patient update(Patient patientDetails, Patient patientToUpdate) {
        patientToUpdate.setLastName(patientDetails.getLastName());
        patientToUpdate.setFirstName(patientDetails.getFirstName());
        patientToUpdate.setAddress(patientDetails.getAddress());
        patientToUpdate.setSex(patientDetails.getSex());
        patientToUpdate.setBirthdate(patientDetails.getBirthdate());
        patientToUpdate.setPhoneNumber(patientDetails.getPhoneNumber());

        return patientRepository.save(patientToUpdate);
    }

    public void delete(Patient patient) { patientRepository.delete(patient); }

}
