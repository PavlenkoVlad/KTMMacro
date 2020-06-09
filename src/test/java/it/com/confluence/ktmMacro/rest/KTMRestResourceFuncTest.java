package it.com.confluence.ktmMacro.rest;

import org.junit.Test;
import static org.junit.Assert.*;
import com.confluence.ktmMacro.rest.KTMRestResourceModel;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

public class KTMRestResourceFuncTest {

    @Test
    public void ktmRestIntegrationTest() {
        String baseUrl = System.getProperty("baseurl");
        String resourceUrl = baseUrl + "/rest/ktm-rest/latest/message";
        RestClient client = new RestClient();
        Resource resource = client.resource(resourceUrl);
        KTMRestResourceModel message = resource.get(KTMRestResourceModel.class);
        assertEquals("ktmRestTest result: ","Hello World",message.getMessage());
    }

}
