package patients.controller;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import patients.dto.PatientDto;
import patients.exception.ResourceNotFoundException;
import patients.model.Patient;
import patients.service.PatientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class PatientController {

    private static final Logger logger = LogManager.getLogger(PatientController.class);
    private PatientService patientService;

    @GetMapping("/patient")
    public ResponseEntity<List<Patient>> getAllPatients() {
        logger.info("method getAllPatients called");
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable long id) throws ResourceNotFoundException {
        logger.info("method getPatientById called for id = " + id);
        Optional<Patient> optionalPatient = patientService.findById(id);

        if (optionalPatient.isEmpty()) {
            logger.error("Patient not found for id = " + id);
            throw new ResourceNotFoundException("Patient not found for id = " + id);
        }

        return ResponseEntity.ok(optionalPatient.get());
    }

    @PostMapping("/patient/add")
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody PatientDto patientDto) {
        logger.info("method createPatient called " + patientDto.toString());

        return new ResponseEntity<>(patientService.save(patientDto), HttpStatus.CREATED);
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<Patient> updatePatient(@Valid @RequestBody PatientDto patientDetails, @PathVariable long id) throws ResourceNotFoundException {
        logger.info("method updatePatient called [ id = " + id +
                " ; patientDetails = " + patientDetails.toString() + " ]");

        Optional<Patient> optionalPatient = patientService.findById(id);

        if (optionalPatient.isEmpty()) {
            logger.error("Patient not found for id = " + id);
            throw new ResourceNotFoundException("Patient not found for id = " + id);
        }

        return new ResponseEntity<>(patientService.update(patientDetails, optionalPatient.get()), HttpStatus.OK);
    }

    @DeleteMapping("/patient/{id}")
    public Map<String, Boolean> deletePatient(@PathVariable long id) throws ResourceNotFoundException {
        logger.info("method deletePatient called for id = " + id);
        Optional<Patient> optionalPatient = patientService.findById(id);

        if (optionalPatient.isEmpty()) {
            throw new ResourceNotFoundException("Patient not found for id = " + id);
        }

        patientService.delete(optionalPatient.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
