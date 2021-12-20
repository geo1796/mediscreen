package Report.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "patient", url = "localhost:8081")
public interface PatientClient {

    //TODO getPatientById

}

/*
@FeignClient(name = "gpsUtil", url = "${gpsUtil.base-path}")
public interface GpsUtilClient {

    @RequestMapping(method = RequestMethod.GET, value = "/getAttractions")
    List<Attraction> getAttractions();

    @RequestMapping("/getLocation/{userId}")
    VisitedLocation getUserLocation(@PathVariable("userId") UUID userId);

}
 */