package patients;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import patients.controller.PatientController;
import patients.dto.PatientDto;

import java.time.LocalDate;

@SpringBootApplication()
@AllArgsConstructor
@PropertySource("application.properties")
public class App implements CommandLineRunner {

    private PatientController patientController;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientController.createPatient(new PatientDto("Son", "Goku", "M",
                "Namek", "0123", "1998-04-29"));

        patientController.getAllPatients().getBody().forEach(
                patient -> System.out.println(patient.toString())
        );

        String id = patientController.getAllPatients().getBody().get(0).getId();
        patientController.updatePatient(new PatientDto("Vegeta", "Vegeta", "M",
                "planet Vegeta", "6789", "1995-05-25"), id);

        patientController.getAllPatients().getBody().forEach(
                patient -> System.out.println(patient.toString())
        );
    }
}
