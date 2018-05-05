package nz.co.rajees.springboot.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * A Data transfer object used to deserialize data sent to a Rest API
 */
public class User {

    @NotBlank
    @Size(min = 1, max = 10)
    private String name;

    //@JsonDeserialize(using = CustomDateSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date birthday;

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
}
