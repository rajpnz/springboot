package nz.co.rajees.springboot.other;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * This test class has been set up to test adhoc or other libraries that I have found during
 * my ongoing learning about Java.
 *
 */
public class OtherTests {


    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    /**
     * The {@link OutputCapture} captures the output of System.out and System.err. Good for checking log outputs.
     * Also see <a href="https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html">
     *     Springboot Testing</a>
     * @see <a href="https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/rule/OutputCapture.html">
     *     Spring Boot Java Docs</a>
     */
    @Test
    public void testOutputCapture() {
        System.out.println("Hello");
        assertThat(outputCapture.toString(), containsString("Hello"));
    }
}
