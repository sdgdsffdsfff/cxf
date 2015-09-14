import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.junit.Test;

import cn.zno.pojo.News;
import cn.zno.pojo.Param;

import com.thoughtworks.xstream.XStream;

public class TestNews {
	@Test
	public void testJson() throws Exception {
		// provider
		List<JacksonJsonProvider> provider = Collections
				.singletonList(new JacksonJsonProvider());

		// ����bean
		Param param = new Param();
		param.setName("some json string");
		param.setWordNumber(10);

		// �����ͻ���
		WebClient client = WebClient
				.create("http://localhost:8080/wadl/news/requestNews", provider)
				.accept("application/json")
				.type("application/json");

		// post �����������ܽ��
		Response r = client.post(param);

		// ״̬
		System.out.println(r.getStatus());

		// ������json������Ϊbean
		MappingJsonFactory factory = new MappingJsonFactory();
		JsonParser parser = factory.createJsonParser((InputStream) r.getEntity());
		News news = parser.readValueAs(News.class);
		// ��ӡ
		System.out.println(news.toString());
	}

	@Test
	public void testXml() throws Exception {
		// ����ֻװ��һ��Provider
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JAXBElementProvider<Param>());

		Param param = new Param();
		param.setName("some xml string");
		param.setWordNumber(20);

		XStream xstream = new XStream();
		// ����bean�����
		xstream.alias("param", Param.class);
		xstream.alias("news", News.class);

		WebClient client = WebClient
				.create("http://localhost:8080/wadl/news/requestNews", providers)
				.accept("application/xml")
				.type("application/xml");
		Response r = client.post(xstream.toXML(param));
		// ״̬
		System.out.println(r.getStatus());
		// תbean
		InputStream is = (InputStream) r.getEntity();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				"utf-8"));
		News news = (News)xstream.fromXML(reader);
		System.out.println(xstream.toXML(news));
	}
}
