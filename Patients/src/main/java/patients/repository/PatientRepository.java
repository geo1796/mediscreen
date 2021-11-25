package patients.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import patients.model.Patient;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
}
