package nz.co.rajees.springboot;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Tests using {@link com.github.tomakehurst.wiremock.WireMockServer} not
 * {@link WireMockRule}
 */
public class WireMockServerTests {

    private static final int WIRE_MOCK_PORT = 8089;

    private WireMockServer wireMockServer;

    @Before
    public void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(WIRE_MOCK_PORT));
        wireMockServer.start();
        WireMock.configureFor(WIRE_MOCK_PORT );
    }

    @After
    public void tearDown() {
        WireMock.reset();
        wireMockServer.stop();
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

   /*Helper methods*/
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
