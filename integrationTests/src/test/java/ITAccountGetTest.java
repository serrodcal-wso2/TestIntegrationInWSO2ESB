import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java8.En;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Created by LN97YU on 17/01/2018.
 */
public class ITAccountGetTest implements En {

    private final static int wireMockPort = 8080;
    private WireMockServer wireMockServer = new WireMockServer(wireMockPort);
    private final static String hostName = "localhost";
    private final static String protocol = "http://";
    private final static String path = "/bank/accounts";
    private final static String url = protocol + hostName + ":" + "8280" + path;
    private final static String jsonToReturn = "{\"account\":\"00301020451234567890\"}";

    public ITAccountGetTest() {
        When("I make a request to account API with a GET", () -> {
            makeGetRequestToAccountAPI();
        });
    }

    public void makeGetRequestToAccountAPI() throws IOException, InterruptedException {
        //TOREMOVE: long time = 15000;
        //TOREMOVE: Thread.sleep(time);
        wireMockServer.start();
        configureFor(hostName, wireMockPort);
        stubFor(get(urlEqualTo(path)).
                willReturn(aResponse().withHeader("Content-type", "application/json").withBody(jsonToReturn)));

        String response = doGetRequest();
        //TOREMOVE: System.out.println(response);
        //Check the result of the call
        Assert.assertEquals(response, jsonToReturn);
        //Check if the request where made to wiremock
        verify(getRequestedFor(urlMatching(path)));

        wireMockServer.stop();

    }

    private String doGetRequest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-Type","application/json");
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        BufferedReader in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
