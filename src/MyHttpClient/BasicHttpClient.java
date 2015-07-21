package MyHttpClient;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public interface BasicHttpClient {
	public static final CloseableHttpClient HTTPCLIENT=HttpClients.createDefault();
}
