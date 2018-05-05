package nz.co.rajees.springboot.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;
import java.util.Date;

/**
 * Perform custom DateSerialization using Jackson
 * @see <a href="">http://www.baeldung.com/jackson-serialize-dates</a>
 */
public class CustomDateSerializer extends StdDeserializer<Date> {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = ISODateTimeFormat.dateTime();

    public static void main(String[] args) {
        System.out.println(ISODateTimeFormat.dateTime().print(DateTime.now()));
    }

    public CustomDateSerializer() {
        this(null);
    }
    protected CustomDateSerializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String date = jsonParser.getText();
        // date in format 2018-05-05T13:13:09.588+12:00

        DateTime dateTime = DATE_TIME_FORMATTER.parseDateTime(date);
        Date result = dateTime.toDate();
        if(!hasParsedStrictly(date, dateTime)) {
            throw new RuntimeException("Incorrect Date format");
        }
        return result;
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
        return dateSupplied.equals(DATE_TIME_FORMATTER.print(parsedDate));
    }
}

