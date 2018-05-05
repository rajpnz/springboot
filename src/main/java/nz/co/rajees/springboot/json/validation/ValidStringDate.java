package nz.co.rajees.springboot.json.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StringDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStringDate {
    // used to get later in the resource bundle the i18n text
    String message() default "Unhappy Strings = Unhappy Life";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
