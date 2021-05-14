import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Set {
    public static void main (String []args) throws IOException, ParseException {

        List<String> usernameList = Post.load("D:\\ProjectF\\Utility\\resource\\name.txt");
        List<String> passwordList = Post.load("D:\\ProjectF\\Utility\\resource\\pass.txt");
        List<String> itemList = Post.load("D:\\ProjectF\\Utility\\resource\\item_id");
        List<String> actionList = Post.load("D:\\ProjectF\\Utility\\resource\\action");
        List<String> categoryList = Post.load("D:\\ProjectF\\Utility\\resource\\category");

        for(int index = 0 ; index < 563; ++index) {
            String item = itemList.get(index);
            String category = returnCategory(item);

            //Change the URL with any other publicly accessible POST resource, which accepts JSON request body
            HttpURLConnection con = setConnection();

            //JSON String need to be constructed for the specific resource.
            //We may construct complex JSON using any third-party JSON libraries such as jackson or org.json
            String jsonInputString = toJson(item, category);
            System.out.println(jsonInputString);
            doPost(con, jsonInputString);
        }
    }

    public static Boolean ifContain(List<String> attributeList, String item) {

        for(String obj : attributeList) {
            if(item.contains(obj)) {
                return true;
            }
        }

        return false;
    }

    public static String toJson(String item, String category){
        String jsonInputString =
                "{\n" +
                        "    \"event\": \"$set\",\n" +
                        "    \"entityType\": \"" + "item" + "\",\n" +
                        "    \"entityId\": \""+ item + "\",\n" +
                        "    \"properties\": {\n" +
                        "          \"category\":" + "[" + category + "]\n" +
                        "    }, \n" +
                        "    \"eventTime\": \""+ LocalDateTime.now()+"Z" + "\"\n" +
                        "}";

        return jsonInputString;
    }

    public static void doPost(HttpURLConnection con, String jsonInputString) throws IOException {
        try(OutputStream os = con.getOutputStream()){
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
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

    public static HttpURLConnection setConnection() throws IOException {
        URL url = new URL ("http://localhost:9090/engines/pcrs_change/events");

        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);
        return con;
    }

    public static String returnCategory(String item) {
        String category = new String();
        if(ifContain(Resource.intel_1151_cpu_category, item)) {
            category = "\"intel\", " + "\"cpu\", " + "\"1151\"";
        } else if (ifContain(Resource.intel_1151_main_category, item)) {
            category = "\"intel\", " + "\"mainboard\", " + "\"1151\"";
        } else if (ifContain(Resource.intel_1200_cpu_category, item)) {
            category = "\"intel\", " + "\"cpu\", " + "\"1200\"";
        } else if (ifContain(Resource.intel_1200_main_category, item)) {
            category = "\"intel\", " + "\"mainboard\", " + "\"1200\"";
        } else if (ifContain(Resource.amd_cpu_category, item)) {
            category = "\"amd\", " + "\"cpu\", " + "\"am4\"";
        } else if (ifContain(Resource.amd_main_category, item)) {
            category = "\"amd\", " + "\"mainboard\", " + "\"am4\"";
        } else if (ifContain(Resource.nvidia_gpu_category, item)) {
            category = "\"nvidia\", " + "\"gpu\"";
        } else if (ifContain(Resource.amd_gpu_category, item)) {
            category = "\"amd\", " + "\"gpu\"";
        } else if (ifContain(Resource.psu_category, item)) {
            category = "\"psu\"";
        } else if (ifContain(Resource.ssd_category, item)) {
            category = "\"ssd\"";
        } else if (ifContain(Resource.hdd_category, item)) {
            category = "\"hdd\"";
        } else {
            category = "\"ram\"";
        }
        return category;
    }

}
