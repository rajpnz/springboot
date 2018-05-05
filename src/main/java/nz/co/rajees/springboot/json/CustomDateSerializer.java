package nz.co.rajees.springboot.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;
import java.util.Date;

/**
 * Perform custom DateSerialization using Jackson
 * @see <a href="">http://www.baeldung.com/jackson-serialize-dates</a>
 */
public class CustomDateSerializer extends StdDeserializer<Date> {

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

        DateTime dateTime = ISODateTimeFormat.dateTime().parseDateTime(date);
        return dateTime.toDate();
    }
}

