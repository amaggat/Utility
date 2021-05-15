import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class Time {


    public static void main(String[] args) throws ParseException, IOException {

        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now()+"Z");
        int x =(new Random().nextInt())%6;
        System.out.println(x);
        System.out.println((Math.abs(x)));

        //Change the URL with any other publicly accessible POST resource, which accepts JSON request body
        URL url = new URL ("http://localhost:9090/engines/pcrs_demo/events");

        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);

        //JSON String need to be constructed for the specific resource.
        //We may construct complex JSON using any third-party JSON libraries such as jackson or org.json
        String jsonInputString = "{\n" +
                "    \"event\": \""+ "view" + "\",\n" +
                "    \"entityType\": \""+ "user" + "\",\n" +
                "    \"entityId\":"+ "\"9\"" +",\n" +
                "    \"targetEntityType\": \"" + "item" + "\",\n" +
                "    \"targetEntityId\": \""+ "corsair-vengeance-RGB-pro-1x16G-3000" + "\",\n" +
                "    \"eventTime\": \""+ LocalDateTime.now().toString()+"Z" + "\"\n" +
                "}";
        System.out.println(jsonInputString);

        try(OutputStream os = con.getOutputStream()){
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = con.getResponseCode();
        System.out.println(code);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
