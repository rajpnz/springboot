package nz.co.rajees.springboot.json.validation;

import org.joda.time.DateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * Created by rajpn on 6/05/2018.
 * @see <a href="http://sterl.org/2015/06/java-jsr-validation-for-dates/">Valdiation Example</a>
 */
public class DateValidator implements ConstraintValidator<ValidDate, Date> {

    private ValidDate constraintAnnotation;

    @Override
    public void initialize(ValidDate validDate) {
        constraintAnnotation = validDate;
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        DateTime dateToValidate = new DateTime(date);
        DateTime now = DateTime.now();
        DateTime nowPlusOneDay = now.plusDays(1);
        DateTime nowMinusOneDay = now.minusDays(1);
        boolean isValid = nowMinusOneDay.isBefore(dateToValidate) && nowPlusOneDay.isAfter(dateToValidate);
        return isValid;
    }
}
