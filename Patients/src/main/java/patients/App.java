package patients;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import patients.controller.PatientController;
import patients.dto.PatientDto;

@SpringBootApplication()
@AllArgsConstructor
public class App implements CommandLineRunner {

    private PatientController patientController;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientController.createPatient(new PatientDto(
                "Son", "Goku", "M", "Namek", "0123456789", "1998-04-29"));
    }
}
