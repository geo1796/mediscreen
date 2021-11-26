package patients.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Patient")
@DynamicUpdate
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lastName;
    private String firstName;
    private String sex;
    private String address;
    private String phoneNumber;
    private LocalDate birthdate;

    public Patient(String lastName, String firstName, String sex, String address, String phoneNumber, LocalDate birthdate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Patient : [ lastName = " + lastName +
                " / firstName = " + firstName +
                " / sex = " + sex +
                " / address = " + address +
                " / phoneNumber = " + phoneNumber +
                " / dateOfBirth = " + birthdate + " ]";
    }
}
