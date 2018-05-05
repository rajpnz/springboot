package nz.co.rajees.springboot.dto;

import nz.co.rajees.springboot.json.validation.ValidDate;
import nz.co.rajees.springboot.json.validation.ValidStringDate;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * A Data transfer object used to deserialize data sent to a Rest API
 */
public class User {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = ISODateTimeFormat.dateTime();

    @NotBlank
    @Size(min = 1, max = 10)
    private String name;

    //@JsonDeserialize(using = CustomDateSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @ValidDate
    private Date birthday;

    @ValidStringDate
    private String birthdayAsString;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdayAsString() {
        return birthdayAsString;
    }

//    public void setBirthdayAsString(String birthdayAsString) {
//        this.birthdayAsString = birthdayAsString;
//    }

    public Date getBirthdayAsStringAsDate() {
        return DATE_TIME_FORMATTER.parseDateTime(birthdayAsString).toDate();
    }
}
