package patients.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
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
            return LocalDate.parse(birthdate).isBefore(ChronoLocalDate.from(Instant.now()));
        }
        catch (DateTimeParseException e) {
            return false;
        }
    }
}
