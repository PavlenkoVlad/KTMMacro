package ut.com.confluence.ktmMacro.rest;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.confluence.ktmMacro.rest.KTMRestResource;
import com.confluence.ktmMacro.rest.KTMRestResourceModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;

public class KTMRestResourceTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void messageIsValid() {
        KTMRestResource resource = new KTMRestResource();

        Response response = resource.getMessage();
        final KTMRestResourceModel message = (KTMRestResourceModel) response.getEntity();

        assertEquals("wrong message","Hello World",message.getMessage());
    }
}
