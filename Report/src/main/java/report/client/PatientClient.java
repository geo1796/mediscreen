package report.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import report.model.Patient;


@FeignClient(name = "patient", url = "${patient.base-path}")
public interface PatientClient {

    @RequestMapping(method = RequestMethod.GET, value = "/patient/{patientId}")
    Patient getPatientById(@PathVariable("patientId") long patientId);

}