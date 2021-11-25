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

    public Patient(String lastName, String firstName, String sex, String address, String phoneNumber, LocalDate dateOfBirth) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Patient : [ lastName = " + lastName +
                " / firstName = " + firstName +
                " / sex = " + sex +
                " / address = " + address +
                " / phoneNumber = " + phoneNumber +
                " / dateOfBirth = " + dateOfBirth + " ]";
    }
}
