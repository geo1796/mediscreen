package report.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import patients.model.Patient;


@FeignClient(name = "patient", url = "localhost:8081")
public interface PatientClient {

    @RequestMapping(method = RequestMethod.GET, value = "/patient/{patientId}")
    Patient getPatientById(@PathVariable("patientId") long patientId);

}