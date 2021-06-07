package PCRS;

import PCRS.model.ChatbotResult;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Chatbot {

    public static final String URL_CHATBOT = "http://localhost:5000/chatbot";

    private static HttpURLConnection setConnection(String path) throws IOException {
        URL url = new URL(path);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);
        return con;
    }

    private static String toJsonChatbot(String content) {
        String json = new String();
        json = "{\"content\": \"" + content + "\"}";
        System.out.println(json);
        return json;
    }

    private static ChatbotResult outChatbotResponse(HttpURLConnection con) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Gson gson = new Gson();
            ChatbotResult result = gson.fromJson(response.toString(), ChatbotResult.class);

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ChatbotResult();
    }

    public static ChatbotResult returnContent(String content) throws IOException {
        HttpURLConnection con = setConnection(URL_CHATBOT);
        String jsonChat = toJsonChatbot(content);
        Set.doPost(con, jsonChat);
        ChatbotResult contentReturn = outChatbotResponse(con);

        return contentReturn;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(returnContent("Hello").getContent());
    }
}
