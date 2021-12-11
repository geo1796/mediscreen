package patients.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class BirthdateConstraintValidator implements ConstraintValidator<ValidBirthdate, String> {
    @Override
    public void initialize(ValidBirthdate constraintAnnotation) {
    }

    @Override
    public boolean isValid(String birthdate, ConstraintValidatorContext constraintValidatorContext) {
        if (birthdate == null)
            return false;

        try{
            return LocalDate.parse(birthdate).isEqual(LocalDate.now()) || LocalDate.parse(birthdate).isBefore(LocalDate.now());
        }
        catch (DateTimeParseException e) {
            return false;
        }
    }
}
