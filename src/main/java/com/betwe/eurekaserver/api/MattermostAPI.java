package com.betwe.eurekaserver.api;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.betwe.eurekaserver.constant.MATTERMOST_CONST;

/**
 * @author imjh8
 *
 */
public class MattermostAPI {

    /**
     * @param channel
     * @param message
     * @throws IOException
     */
    public static void sendMessage(String channel, String message) throws IOException {
        System.out.println("Mattermost sendMessage Start>>>>>>>> " + message);
        
        // SSLContext 설정
        SSLContext sslContext;
		try {
			sslContext = SSLContextBuilder.create()
			        .loadTrustMaterial(new TrustSelfSignedStrategy())
			        .build();

	        // HttpClient 설정
	        HttpClient httpClient = HttpClientBuilder.create()
	                .setSSLContext(sslContext)
	                .build();
	        
	        // TLS 버전 설정 (옵션)
	        System.setProperty("https.protocols", "TLSv1.2");
	        
	        //CloseableHttpClient httpClient = HttpClients.createDefault();
	        HttpPost request = new HttpPost(MATTERMOST_CONST.API_URL);
	
	        // 웹훅으로 전송할 메시지를 JSON 형식으로 작성합니다.
	        String requestBody = "{\"username\": \"chatbot\", \"channel\":\"" + channel + "\", \"attachments\": [{\"text\":\"" + message + "\"}]}";
	
	        // JSON 형식의 메시지를 요청 바디에 설정합니다.
	        request.setEntity(new StringEntity(requestBody));
	
	        // 요청을 보내고 응답을 받습니다.
	        HttpResponse response = httpClient.execute(request);
	
	        // 응답 처리
	        HttpEntity entity = response.getEntity();
	        String responseBody = EntityUtils.toString(entity);
	        System.out.println("Webhook response: " + responseBody);
	        
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
