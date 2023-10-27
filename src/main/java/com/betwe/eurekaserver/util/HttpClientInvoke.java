package com.betwe.eurekaserver.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author imjh8
 *
 */
public class HttpClientInvoke {

    /**
     * HTTP GET 요청을 보내고 응답을 받아옵니다.
     * 
     * @param url      요청할 URL 문자열
     * @param encoding 요청 및 응답에 사용할 문자 인코딩
     * @return 서버 응답 내용
     * @throws IOException 입출력 예외가 발생할 경우
     */
    public static String sendHttpRequest(String url, String encoding) throws IOException {
        HttpURLConnection conn = null;
        BufferedReader in = null;
        
        try {
            // URL 객체 생성
            URL urlObj = new URL(url);
            
            // HttpURLConnection 객체 생성 및 설정
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            
            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 응답 내용 읽기
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                String inputLine;
                StringBuilder response = new StringBuilder();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                
                return response.toString();
            } else {
                throw new IOException("HTTP 요청 실패: " + responseCode);
            }
        } finally {
            // 연결 및 리소스 해제
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
    
    /**
     * HTTPS GET 요청을 보내고 응답을 받아옵니다.
     * 
     * @param url      요청할 URL 문자열
     * @param encoding 요청 및 응답에 사용할 문자 인코딩
     * @return 서버 응답 내용
     * @throws IOException 입출력 예외가 발생할 경우
     */
    public static String sendHttpsRequest(String url, String encoding) throws IOException {
        HttpsURLConnection conn = null;
        BufferedReader in = null;
        
        try {
            // URL 객체 생성
            URL urlObj = new URL(url);
            
            // HttpsURLConnection 객체 생성 및 설정
            conn = (HttpsURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            
            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                // 응답 내용 읽기
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                String inputLine;
                StringBuilder response = new StringBuilder();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                
                return response.toString();
            } else {
                throw new IOException("HTTPS 요청 실패: " + responseCode);
            }
        } finally {
            // 연결 및 리소스 해제
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
    
    /**
     * HTTP POST 요청을 보내고 응답을 받아옵니다.
     * 
     * @param url       요청할 URL 문자열
     * @param parameter 요청에 포함할 파라미터 문자열
     * @param encoding  요청 및 응답에 사용할 문자 인코딩
     * @return 서버 응답 내용
     * @throws IOException 입출력 예외가 발생할 경우
     */
    public static String sendHttpPostRequest(String url, String parameter, String encoding) throws IOException {
        HttpURLConnection conn = null;
        BufferedReader in = null;
        OutputStream out = null;
        
        try {
            // URL 객체 생성
            URL urlObj = new URL(url);
            
            // HttpURLConnection 객체 생성 및 설정
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            // 파라미터 전송
            byte[] postData = parameter.getBytes(encoding);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postData.length));
            out = conn.getOutputStream();
            out.write(postData);
            
            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 응답 내용 읽기
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                String inputLine;
                StringBuilder response = new StringBuilder();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                
                return response.toString();
            } else {
                throw new IOException("HTTP POST 요청 실패: " + responseCode);
            }
        } finally {
            // 연결 및 리소스 해제
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
    
    /**
     * HTTPS POST 요청을 보내고 응답을 받아옵니다.
     * 
     * @param url       요청할 URL 문자열
     * @param parameter 요청에 포함할 파라미터 문자열
     * @param encoding  요청 및 응답에 사용할 문자 인코딩
     * @return 서버 응답 내용
     * @throws IOException 입출력 예외가 발생할 경우
     */
    public static String sendHttpsPostRequest(String url, String parameter, String encoding) throws IOException {
        HttpsURLConnection conn = null;
        BufferedReader in = null;
        OutputStream out = null;
        
        try {
            // URL 객체 생성
            URL urlObj = new URL(url);
            
            // HttpsURLConnection 객체 생성 및 설정
            conn = (HttpsURLConnection) urlObj.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            // 파라미터 전송
            byte[] postData = parameter.getBytes(encoding);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postData.length));
            out = conn.getOutputStream();
            out.write(postData);
            
            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                // 응답 내용 읽기
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                String inputLine;
                StringBuilder response = new StringBuilder();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                
                return response.toString();
            } else {
                throw new IOException("HTTPS POST 요청 실패: " + responseCode);
            }
        } finally {
            // 연결 및 리소스 해제
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            // GET 요청 예시
            String getUrl = "http://example.com";
            String getResponse = sendHttpRequest(getUrl, "UTF-8");
            System.out.println("GET 응답: " + getResponse);
            
            String httpsUrl = "https://example.com";
            String httpsResponse = sendHttpsRequest(httpsUrl, "UTF-8");
            System.out.println("HTTPS 응답: " + httpsResponse);
            
            // POST 요청 예시
            String postUrl = "http://example.com";
            String parameter = "param1=" + URLEncoder.encode("value1", "UTF-8")
                    + "&param2=" + URLEncoder.encode("value2", "UTF-8");
            String postResponse = sendHttpPostRequest(postUrl, parameter, "UTF-8");
            System.out.println("POST 응답: " + postResponse);
            
            String httpsPostUrl = "https://example.com";
            String httpsParameter = "param1=" + URLEncoder.encode("value1", "UTF-8")
                    + "&param2=" + URLEncoder.encode("value2", "UTF-8");
            String httpsPostResponse = sendHttpsPostRequest(httpsPostUrl, httpsParameter, "UTF-8");
            System.out.println("HTTPS POST 응답: " + httpsPostResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * HTTP 또는 HTTPS 요청을 보내고 응답을 받아옵니다.
     * 
     * @param method    HTTP 요청 메소드 (GET, POST 등)
     * @param url       요청할 URL 문자열
     * @param params    요청에 추가할 매개변수 (파라미터)
     * @param encoding  요청 및 응답 데이터의 인코딩 방식
     * @return 서버 응답 내용
     * @throws IOException 입출력 예외가 발생할 경우
     */
    public static String sendHttpRequest(String method, String url, String params, String encoding) throws IOException {
        HttpURLConnection conn = null;
        
        try {
            // URL 객체 생성
            URL requestUrl = new URL(url);
            
            // HTTP 또는 HTTPS 연결 설정
            if (url.startsWith("https")) {
                conn = (HttpsURLConnection) requestUrl.openConnection();
            } else {
                conn = (HttpURLConnection) requestUrl.openConnection();
            }
            
            // 요청 메소드 설정
            conn.setRequestMethod(method);
            
            // 파라미터가 존재하는 경우 POST 요청 설정
            if (params != null && !params.isEmpty()) {
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                
                // 파라미터 인코딩
                params = URLEncoder.encode(params, encoding);
                
                // 파라미터 전송
                try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                    wr.writeBytes(params);
                    wr.flush();
                }
            }
            
            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 응답 내용 읽기
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                String inputLine;
                StringBuilder response = new StringBuilder();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                return response.toString();
            } else {
                throw new IOException("HTTP 요청 실패: " + responseCode);
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
