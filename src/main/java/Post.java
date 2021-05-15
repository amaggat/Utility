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
        int max = 200000;

        for(int index = 0 ; index < max; ++index) {
            int x = Math.abs(new Random().nextInt());
            int userid = x%2412;
            String item = itemList.get(x%562);
            String action = actionList.get(x%8);

            //Change the URL with any other publicly accessible POST resource, which accepts JSON request body
            HttpURLConnection con = Set.setConnection("http://localhost:9090/engines/pcrs_change/events");

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

            System.out.println(jsonInputString);

            Set.doPost(con, jsonInputString);
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
