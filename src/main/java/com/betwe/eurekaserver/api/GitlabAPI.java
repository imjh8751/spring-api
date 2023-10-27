package com.betwe.eurekaserver.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.betwe.eurekaserver.constant.GITLAB_CONST;

/**
 * @author imjh8
 *
 */
public class GitlabAPI {

    /**
     * @return
     * @throws IOException
     */
    public static String getUsers() throws IOException {
        
        // SSLContext 설정
        SSLContext sslContext;
        HttpEntity entity;
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
	        HttpPost request = new HttpPost(GITLAB_CONST.GITLAB_URL + GITLAB_CONST.GITLAB_API_URL + "/users");
	
	        // HEADER 를 지정합니다
	        request.setHeader("Private-Token", GITLAB_CONST.GITLAB_PRIVATE_TOKEN);
	        request.setEntity(null);
	
	        // 요청을 보내고 응답을 받습니다.
	        HttpResponse response = httpClient.execute(request);
	
	        // 응답 처리
	        entity = response.getEntity();
	        String responseBody = EntityUtils.toString(entity);
	        System.out.println("Gitlab response: " + responseBody);
	        
	        return responseBody;
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
		return null;
    }

    /**
     * @throws IOException
     */
    public static void getUsers2() throws IOException {
        try {

            URL url = new URL(GITLAB_CONST.GITLAB_URL + GITLAB_CONST.GITLAB_API_URL + "/users");
            
            // TLS 1.2를 사용하기 위해 SSLContext를 생성하고 초기화
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            
            // SSLContext에서 SSLSocketFactory를 가져옴
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(socketFactory);

            connection.setRequestMethod("GET");
            connection.setRequestProperty("PRIVATE-TOKEN", GITLAB_CONST.GITLAB_PRIVATE_TOKEN);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("Error: " + responseCode);
            }
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
    
}
