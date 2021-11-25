package patients.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
public class Patient {

    @Id
    private String id;
    private String lastName;
    private String firstName;
    private String sex;
    private String address;
    private String phoneNumber;
    private LocalDate dateOfBirth;

}
