import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Post {

    public static void main (String []args) throws IOException, ParseException {

        List<String> usernameList = load("D:\\ProjectF\\Utility\\resource\\name.txt");
        List<String> passwordList = load("D:\\ProjectF\\Utility\\resource\\pass.txt");
        List<String> itemList = load("D:\\ProjectF\\Utility\\resource\\item_id");
        List<String> actionList = load("D:\\ProjectF\\Utility\\resource\\action");
        int max = 2000;

        for(int index = 0 ; index < max; ++index) {
            int x = Math.abs(new Random().nextInt());
            int userid = (x%2419)+1;
            String item = itemList.get(x%562);
            String action = actionList.get(x%3);
            Integer retailer = x%3 +1;

            String itemType = new String();
            String API = new String();

            if(action.contains("star")) {
                double rating = returnRating(action);
                itemType = returnItemType(item).getKey();
                API = returnItemType(item).getValue();


                HttpURLConnection con_1 = Set.setPostConnection("http://localhost:8080/user/rating/" + API);
                con_1.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbHRlcmllc3RhIiwiZXhwIjoxNjIxNTc2MTk1LCJpYXQiOjE2MjEyMTYxOTV9.2Qv5F2hAXkIv_utzUVhfBoCS5btVhCnXe__nlHkJrSI");
                con_1.setRequestProperty("Cookie", "userid=" + userid);
                String jsonAction = toJson(userid, action, itemType, rating);
                System.out.println(action + " " + retailer);
                Set.doPost(con_1, jsonAction);
            } else if(action.contains("view")) {
                API = returnItemType(item).getValue();
                HttpURLConnection con = Set.setGetConnection("http://localhost:8080/api/" + API + "/" + item);
                con.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbHRlcmllc3RhIiwiZXhwIjoxNjIxNTc2MTk1LCJpYXQiOjE2MjEyMTYxOTV9.2Qv5F2hAXkIv_utzUVhfBoCS5btVhCnXe__nlHkJrSI");
                con.setRequestProperty("Cookie", "userId=" + userid);

                System.out.println(action);
                Set.doGet(con);
            }

            System.out.println("Action No.: " + index);

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

    public static String returnCategory(String item) {
        String category = new String();
        if(Set.ifContain(Resource.intel_1151_cpu_category, item)) {
            category = "cpu";
        } else if (Set.ifContain(Resource.intel_1151_main_category, item)) {
            category = "mainboard";
        } else if (Set.ifContain(Resource.intel_1200_cpu_category, item)) {
            category = "cpu";
        } else if (Set.ifContain(Resource.intel_1200_main_category, item)) {
            category = "mainboard";
        } else if (Set.ifContain(Resource.amd_cpu_category, item)) {
            category = "cpu";
        } else if (Set.ifContain(Resource.amd_main_category, item)) {
            category = "mainboard";
        } else if (Set.ifContain(Resource.nvidia_gpu_category, item)) {
            category = "gpu";
        } else if (Set.ifContain(Resource.amd_gpu_category, item)) {
            category = "gpu";
        } else if (Set.ifContain(Resource.psu_category, item) && !item.contains("-2x")) {
            category = "psu";
        } else if (Set.ifContain(Resource.ssd_category, item)) {
            category = "ssd";
        } else if (Set.ifContain(Resource.hdd_category, item)) {
            category = "hdd";
        } else {
            category = "ram";
        }
        return category;
    }

    public static Pair<String, String> returnItemType(String item){
        String itemType = new String();
        String API = new String();
        switch (returnCategory(item)){
            case "cpu":
                itemType = "centralProcessor";
                API = "cpu";
                break;
            case "mainboard":
                itemType = "mainboard";
                API = "mainboard";
                break;
            case "gpu":
                itemType = "graphicProcessor";
                API = "gpu";
                break;
            case "ram":
                itemType = "ram";
                API = "ram";
                break;
            case "hdd":
                itemType = "hdd";
                API = "hdd";
                break;
            case "ssd":
                itemType = "ssd";
                API = "ssd";
                break;
            case "psu":
                itemType = "psu";
                API = "psu";
                break;
        }

        return new Pair<>(itemType, API);
    }

    public static double returnRating(String action){
        double rating = 0.0;

        switch (action) {
            case "rate 1 star":
                rating = 1.0;
                break;
            case "rate 2 star":
                rating = 2.0;
                break;
            case "rate 3 star":
                rating = 3.0;
                break;
            case "rate 4 star":
                rating = 4.0;
                break;
            case "rate 5 star":
                rating = 5.0;
                break;
        }

        return rating;
    }

    public static String toJson(int userid, String item, String itemType, double rating){
        return "{\n" +
                "    \"id\": \""+ userid + "-" + item +"\",\n" +
                "    \"user\" : {\n" +
                "        \"id\":"+ userid+ "\n" +
                "    },\n" +
                "\n" +
                "    \"" + itemType +"\" : {\n" +
                "        \"id\" : \""+ item +"\"\n" +
                "    },\n" +
                "\n" +
                "    \"rating\" :"+ rating + ",\n" +
                "    \"favorite\" : false\n" +
                "}";
    }

    //            String jsonInputString = "{\n" +
//                    "    \"event\": \""+ action + "\",\n" +
//                    "    \"entityType\": \""+ "user" + "\",\n" +
//                    "    \"entityId\": \""+ userid +"\",\n" +
//                    "    \"targetEntityType\": \"" + "item" + "\",\n" +
//                    "    \"targetEntityId\": \""+ item + "\",\n" +
//                    "    \"properties\": \" { }\",\n" +
//                    "    \"eventTime\": \""+ LocalDateTime.now()+"Z" + "\"\n" +
//                    "}";

}
