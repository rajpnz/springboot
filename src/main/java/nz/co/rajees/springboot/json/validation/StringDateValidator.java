package nz.co.rajees.springboot.json.validation;

import nz.co.rajees.springboot.dto.User;
import org.joda.time.DateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * Created by rajpn on 6/05/2018.
 */
public class StringDateValidator implements ConstraintValidator<ValidStringDate, String> {


    @Override
    public void initialize(ValidStringDate validStringDateDate) {
    }

    @Override
    public boolean isValid(String dateAsString, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        DateTime dateTime = null;
        try {
            dateTime = User.DATE_TIME_FORMATTER.parseDateTime(dateAsString);
        } catch(IllegalArgumentException  | UnsupportedOperationException ex) {
            isValid = false;
            return isValid;
        }

        if(!hasParsedStrictly(dateAsString, dateTime) || !isDateCurrent(dateTime.toDate())) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isDateCurrent(Date date) {
        DateTime dateToValidate = new DateTime(date);
        DateTime now = DateTime.now();
        DateTime nowPlusOneDay = now.plusDays(1);
        DateTime nowMinusOneDay = now.minusDays(1);
        // leeway is day either side of now
        boolean isCurrent = nowMinusOneDay.isBefore(dateToValidate) && nowPlusOneDay.isAfter(dateToValidate);
        return isCurrent;
    }
    /**
     * Joda does not apply strict rules to parsing dates, it makes some assumptions. See
     * https://stackoverflow.com/questions/18489123/how-can-i-make-joda-datetime-parser-to-only-accept-strings-of-form-yyyymmdd
     * @param dateSupplied The date supplied that needs to be deserialized into a {@link Date}
     * @param parsedDate The date that dateSupplied was parsed into.
     * @return boolean to indicate if strict parsing was applied
     */
    private boolean hasParsedStrictly(String dateSupplied, DateTime parsedDate) {
        // check String equivalence of the dateSupplied and parsedDate
        return dateSupplied.equals(User.DATE_TIME_FORMATTER.print(parsedDate));
    }
}
