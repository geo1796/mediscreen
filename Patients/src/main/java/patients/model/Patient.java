package patients.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import patients.constraint.ValidBirthdate;
import patients.constraint.ValidGender;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Patient")
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "last name is mandatory")
    private String lastName;
    @NotBlank(message = "first name is mandatory")
    private String firstName;
    @ValidGender
    private String sex;
    @NotBlank(message = "address is mandatory")
    private String address;
    @NotBlank(message = "Phone number must be 10 digits")
    @Pattern(regexp="(^$|[0-9]{10})") //only digits are accepted
    private String phoneNumber;
    @ValidBirthdate
    private String birthdate;

    public Patient(String lastName, String firstName, String sex, String address, String phoneNumber, String birthdate) {
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
