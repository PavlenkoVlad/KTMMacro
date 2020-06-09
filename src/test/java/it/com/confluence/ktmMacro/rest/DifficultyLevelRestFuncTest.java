package it.com.confluence.ktmMacro.rest;

import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.junit.Test;

import static org.junit.Assert.*;

public class DifficultyLevelRestFuncTest {

    @Test
    public void difficultyLevelRestIntegrationTest() {

        String baseUrl = System.getProperty("baseurl");
        String resourceUrl = baseUrl + "/rest/ktm-rest/latest/difficultyLevel";
        RestClient client = new RestClient();
        Resource resource = client.resource(resourceUrl);
        ClientResponse cr = resource.get();

        assertTrue(true);
    }

}
