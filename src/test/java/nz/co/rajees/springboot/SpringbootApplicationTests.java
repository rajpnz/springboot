package nz.co.rajees.springboot;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Value("#{${my_prop} + 25}")
	private int myProp;

	@Value("${my_name}")
	private String myName ;


	@Value("#{'${my_name2}' == '' ? 'Zippy' : '${my_name2}'}")
	private String myName2 ;
	/**
	 * Example of using a static method in Spring Expression Language (spel)
	 */
	@Value("#{T(org.apache.commons.lang3.StringUtils).length('${my_name}')}")
	private int myNameLength;

	/**
	 * Example of using a literal property place holder
	 */
	@Value("#{'${my_name}'.length() + 19}")
	private int myNameLength2;

	@Test
	public void contextLoads() {
		System.out.println("Hello");
	}

}
