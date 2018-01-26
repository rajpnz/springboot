package nz.co.rajees.springboot;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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

    private static final int WIRE_MOCK_PORT = 8089;
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WIRE_MOCK_PORT);

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

        //arrange
        givenThat(get(urlEqualTo("/rule/test")).willReturn(aResponse().withBody("Rule test body")));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8089/rule/test");

        //act
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = convertHttpResponseToString(httpResponse);

        //assert
        //verify the get endpoint is hit
        verify(getRequestedFor(urlEqualTo("/rule/test")));
        assertThat(stringResponse, is(Matchers.equalTo("Rule test body")));

    }

    @Test
    public void testWiremockPostVerification() throws IOException {

        //arrange
        stubFor(post(urlEqualTo("/rule/test"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(containing("\"name\": \"raj\""))
                .willReturn(aResponse()
                        .withStatus(200)));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:8089/rule/test");
        request.addHeader("Content-Type", "application/json");
        String jsonRequestEntityString = "{\"name\": \"raj\"}";
        StringEntity entity = new StringEntity(jsonRequestEntityString);
        request.setEntity(entity);

        //act
        HttpResponse response = httpClient.execute(request);

        //assert
        verify(postRequestedFor(urlEqualTo("/rule/test"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(containing(jsonRequestEntityString)));
        assertThat(response.getStatusLine().getStatusCode(), is(200));
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
