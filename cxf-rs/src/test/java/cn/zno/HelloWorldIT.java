package cn.zno;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

public class HelloWorldIT {
    private static String serverUrl = "http://localhost:8080/cxf-rs/a/b";


    @Test
    public void testPing() throws Exception {
        WebClient client = WebClient.create(serverUrl + "/c/echo/SierraTangoNevada");
        Response r = client.accept("text/plain").get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        String value = IOUtils.toString((InputStream)r.getEntity());
        assertEquals("SierraTangoNevada", value);
    }

    @Test
    public void testJsonRoundtrip() throws Exception {
//        List<Object> providers = new ArrayList<Object>();
//        providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
        
        List<org.codehaus.jackson.jaxrs.JacksonJsonProvider> provider = Collections.singletonList(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());
        
        InputBean inputBean = new InputBean();
        inputBean.setVal1("Maple");
        WebClient client = WebClient.create(serverUrl + "/c/jsonBean", provider);
        InputBean jsonBean = client.accept(MediaType.APPLICATION_JSON)
            .type(MediaType.APPLICATION_JSON)
            .post(inputBean,InputBean.class);
        System.out.println(jsonBean.toString());
//        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
//        MappingJsonFactory factory = new MappingJsonFactory();
//        JsonParser parser = factory.createJsonParser((InputStream)r.getEntity());
//        JsonBean output = parser.readValueAs(JsonBean.class);
//        assertEquals("Maple", output.getVal2());
    }
}
