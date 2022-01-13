package report.model;

public class Patient {

    private long id;
    private String lastName;
    private String firstName;
    private String sex;
    private String address;
    private String phoneNumber;
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

    public String getBirthdate() {
        return birthdate;
    }

    public String getSex() {
        return sex;
    }
}
