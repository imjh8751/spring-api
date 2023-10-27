package com.betwe.eurekaserver.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.betwe.eurekaserver.constant.TELEGRAM_CONST;
import com.betwe.eurekaserver.util.BaseAPI;

/**
 * @author imjh8
 *
 */
/**
 * @author imjh8
 *
 */
public class TelegramAPI extends BaseAPI {
	
    /**
     * @param text
     */
    public static void sendMessage(String text) {
        
        try {
            String urlString = TELEGRAM_CONST.BASE_URL + TELEGRAM_CONST.BOT_TOKEN + "/sendMessage?chat_id=" + TELEGRAM_CONST.CHAT_ID + "&text=" + URLEncoder.encode(text, "UTF-8");
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            if (responseCode == 200) {
                System.out.println("Message sent successfully!");
            } else {
                System.out.println("Failed to send message. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param chatId
     */
    public static void getChatInfo(String chatId) {
        try {
            String urlString = TELEGRAM_CONST.BASE_URL + TELEGRAM_CONST.BOT_TOKEN + "/getChat?chat_id=" + chatId;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            if (responseCode == 200) {
                System.out.println("Chat information received successfully!");
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("Failed to get chat information. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query
     */
    public static void searchInlineQuery(String query) {
        try {
            String urlString = TELEGRAM_CONST.BASE_URL + TELEGRAM_CONST.BOT_TOKEN + "/answerInlineQuery?inline_query_id=1&query=" + URLEncoder.encode(query, "UTF-8");
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            if (responseCode == 200) {
                System.out.println("Inline query searched successfully!");
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("Failed to search inline query. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void sendPhoto(String photoUrl, String caption) {
//        try {
//            URL url = new URL(TELEGRAM_CONST.BASE_URL + TELEGRAM_CONST.BOT_TOKEN + "/sendPhoto?chat_id=" + TELEGRAM_CONST.CHAT_ID + "&caption=" + caption);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//
//            InputStream photoStream = new URL(photoUrl).openStream();
//            byte[] photoBytes = IOUtils.toByteArray(photoStream);
//
//            String boundary = "---------------------------" + System.currentTimeMillis();
//            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
//
//            connection.getOutputStream().write(("--" + boundary + "\r\n").getBytes());
//            connection.getOutputStream().write(("Content-Disposition: form-data; name=\"photo\"; filename=\"photo.jpg\"\r\n").getBytes());
//            connection.getOutputStream().write(("Content-Type: image/jpeg\r\n\r\n").getBytes());
//            connection.getOutputStream().write(photoBytes);
//            connection.getOutputStream().write(("\r\n--" + boundary + "--\r\n").getBytes());
//
//            int responseCode = connection.getResponseCode();
//
//            if (responseCode == 200) {
//                System.out.println("Photo sent successfully!");
//            } else {
//                System.out.println("Failed to send photo. Response Code: " + responseCode);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void sendDocument(String documentUrl) {
//        try {
//            URL url = new URL(TELEGRAM_CONST.BASE_URL + TELEGRAM_CONST.BOT_TOKEN + "/sendDocument?chat_id=" + TELEGRAM_CONST.CHAT_ID);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//
//            InputStream documentStream = new URL(documentUrl).openStream();
//            byte[] documentBytes = IOUtils.toByteArray(documentStream);
//
//            String boundary = "---------------------------" + System.currentTimeMillis();
//            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
//
//            connection.getOutputStream().write(("--" + boundary + "\r\n").getBytes());
//            connection.getOutputStream().write(("Content-Disposition: form-data; name=\"document\"; filename=\"document.pdf\"\r\n").getBytes());
//            connection.getOutputStream().write(("Content-Type: application/pdf\r\n\r\n").getBytes());
//            connection.getOutputStream().write(documentBytes);
//            connection.getOutputStream().write(("\r\n--" + boundary + "--\r\n").getBytes());
//
//            int responseCode = connection.getResponseCode();
//
//            if (responseCode == 200) {
//                System.out.println("Document sent successfully!");
//            } else {
//                System.out.println("Failed to send document. Response Code: " + responseCode);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
