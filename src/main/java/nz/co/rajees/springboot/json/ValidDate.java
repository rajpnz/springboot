package nz.co.rajees.springboot.json;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate {
    // used to get later in the resource bundle the i18n text
    String message() default "Not a valid date bro";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
