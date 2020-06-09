package it.com.confluence.ktmMacro.rest;

import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ScopeRest {

    @Test
    public void labelRestIntegrationTest() {

        String baseUrl = System.getProperty("baseurl");
        String resourceUrl = baseUrl + "/rest/ktm-rest/latest/scope";
        RestClient client = new RestClient();
        Resource resource = client.resource(resourceUrl);
        ClientResponse cr = resource.get();

        assertTrue(true);
    }

}
