package patients.mapper;

import org.springframework.stereotype.Component;
import patients.dto.PatientDto;
import patients.model.Patient;

import java.time.LocalDate;

@Component
public class PatientMapper {

    public Patient toEntity(PatientDto patientDto){
        return new Patient(
                patientDto.getLastName(), patientDto.getFirstName(), patientDto.getSex(), patientDto.getAddress(),
                patientDto.getPhoneNumber(), LocalDate.parse(patientDto.getBirthdate())
        );
    }

}
