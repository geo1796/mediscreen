package patients.dto;

import lombok.Getter;
import lombok.Setter;
import patients.constraint.ValidBirthdate;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PatientDto {

    private String id;
    @NotBlank(message = "last name is mandatory")
    private String lastName;
    @NotBlank(message = "first name is mandatory")
    private String firstName;
    @NotBlank(message = "gender is mandatory")
    private String sex;
    @NotBlank(message = "address is mandatory")
    private String address;
    @NotBlank(message = "phone number is mandatory")
    private String phoneNumber;
    @ValidBirthdate
    private String birthdate;

    public PatientDto(String lastName, String firstName, String sex, String address, String phoneNumber, String birthdate) {
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
