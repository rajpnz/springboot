package nz.co.rajees.springboot;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class WireMockTests {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void testWireMockUsingBaeldung() throws IOException {
        //http://www.baeldung.com/introduction-to-wiremock
        stubFor(get(urlPathMatching("/baeldung/.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("\"testing-library\": \"WireMock\"")));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8089/baeldung/wiremock");
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = convertHttpResponseToString(httpResponse);
        verify(getRequestedFor(urlEqualTo("/baeldung/wiremock")));
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        assertEquals("application/json", httpResponse.getFirstHeader("Content-Type").getValue());
        assertEquals("\"testing-library\": \"WireMock\"", stringResponse);
    }


    @Test
    public void testWireMockUsingRajApproach() throws IOException {

        givenThat(get(urlEqualTo("/rule/test")).willReturn(aResponse().withBody("Rule test body")));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8089/rule/test");
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = convertHttpResponseToString(httpResponse);
        assertThat(stringResponse, is(Matchers.equalTo("Rule test body")));

    }

    //helper methods
    private String convertHttpResponseToString(HttpResponse httpResponse) throws IOException {
        InputStream inputStream = httpResponse.getEntity().getContent();
        return convertInputStreamToString(inputStream);
    }
    private String convertInputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        String string = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return string;
    }
}
