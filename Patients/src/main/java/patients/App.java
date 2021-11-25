package patients;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import patients.controller.PatientController;
import patients.model.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        patientController.createPatient(new Patient("Son", "Goku", "M",
                "Namek", "0123", LocalDate.parse("29/04/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        patientController.getAllPatients().getBody().forEach(
                patient -> System.out.println(patient.toString())
        );

        String id = patientController.getAllPatients().getBody().get(0).getId();
        patientController.updatePatient(new Patient("Vegeta", "Vegeta", "M",
                "planet Vegeta", "6789", LocalDate.parse("25/05/1995", DateTimeFormatter.ofPattern("dd/MM/yyyy"))), id);

        patientController.getAllPatients().getBody().forEach(
                patient -> System.out.println(patient.toString())
        );
    }
}
