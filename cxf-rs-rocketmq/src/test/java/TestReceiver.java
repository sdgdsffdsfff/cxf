import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import cn.zno.pojo.ReceiverRequest;
import cn.zno.pojo.ReceiverResponse;

public class TestReceiver {

	@Test
	public void testJsonRoundtrip() throws Exception {
		List<Object> providers = new ArrayList<Object>();
		providers.add(new org.codehaus.jackson.jaxrs.JacksonJsonProvider());

		ReceiverRequest request = new ReceiverRequest();
		request.setMsg("hello");

		WebClient client = WebClient.create(
				"http://localhost:8080/cxf-rs-rocketmq/receiver/rece",
				providers);
		ReceiverResponse response = client.accept("application/json")
				.type("application/json").post(request, ReceiverResponse.class);
		System.out.println(response.getCode());

	}

}
