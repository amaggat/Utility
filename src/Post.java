import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Post {

    public static void main (String []args) throws IOException, ParseException {

        List<String> usernameList = load("D:\\ProjectF\\Utility\\resource\\name.txt");
        List<String> passwordList = load("D:\\ProjectF\\Utility\\resource\\pass.txt");
        List<String> itemList = load("D:\\ProjectF\\Utility\\resource\\item_id");
        List<String> actionList = load("D:\\ProjectF\\Utility\\resource\\action");

        for(int index = 0 ; index < 1000; ++index) {
            int x = Math.abs(new Random().nextInt());
            int userid = index%2412;
            String item = itemList.get(x%562);
            String action = actionList.get(x%7);



            //Change the URL with any other publicly accessible POST resource, which accepts JSON request body
            URL url = new URL ("http://localhost:9090/engines/pcrs_demo_4/events");

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            con.setDoOutput(true);

            //JSON String need to be constructed for the specific resource.
            //We may construct complex JSON using any third-party JSON libraries such as jackson or org.json
            String jsonInputString = "{\n" +
                    "    \"event\": \""+ action + "\",\n" +
                    "    \"entityType\": \""+ "user" + "\",\n" +
                    "    \"entityId\": \""+ userid +"\",\n" +
                    "    \"targetEntityType\": \"" + "item" + "\",\n" +
                    "    \"targetEntityId\": \""+ item + "\",\n" +
                    "    \"properties\": \" { }\",\n" +
                    "    \"eventTime\": \""+ LocalDateTime.now()+"Z" + "\"\n" +
                    "}";

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

    public static List<String> load(String path) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader r = new BufferedReader(new FileReader(path));
        try {
            String line;
            while ((line=r.readLine())!=null)
                list.add(line);
        } finally {
            r.close();
        }

        return list;
    }

}
