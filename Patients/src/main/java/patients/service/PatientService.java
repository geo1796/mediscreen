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

    public Patient save(Patient patient) { return patientRepository.save(patient); }

    public List<Patient> findAll() { return patientRepository.findAll(); }

    public Optional<Patient> findById(String id) { return patientRepository.findById(id); }

    public Patient update(Patient PatientDetails, Patient patientToUpdate) {
        patientToUpdate.setLastName(PatientDetails.getLastName());
        patientToUpdate.setFirstName(PatientDetails.getFirstName());
        patientToUpdate.setAddress(PatientDetails.getAddress());
        patientToUpdate.setSex(PatientDetails.getSex());
        patientToUpdate.setDateOfBirth(PatientDetails.getDateOfBirth());
        patientToUpdate.setPhoneNumber(PatientDetails.getPhoneNumber());

        return patientRepository.save(patientToUpdate);
    }

    public void delete(Patient patient) { patientRepository.delete(patient); }
}
