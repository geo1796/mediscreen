package patients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patients.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
