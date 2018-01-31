import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Wiremock {

    public static void main(String[] args) {
        int wireMockPort = 8080;
        WireMockServer wireMockServer = new WireMockServer(options().port(wireMockPort));
        String hostName = "localhost";
        String protocol = "http://";
        String path = "/bank/accounts";
        String jsonToReturn = "{\"account\":\"00301020451234567890\"}";
        String url = protocol + hostName + ":" + "8280" + path;
        wireMockServer.start();
        configureFor(hostName, wireMockPort);
        stubFor(get(urlEqualTo(path)).
                willReturn(aResponse().withHeader("Content-type", "application/json").withBody(jsonToReturn)));
    }

}
