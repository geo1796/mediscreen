package patients.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderConstraintValidator implements ConstraintValidator<ValidGender, String> {
    @Override
    public void initialize(ValidGender constraintAnnotation) {
    }

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        if(gender == null){
            return false;
        }
        return gender.equals("M") || gender.equals("F");
    }
}
