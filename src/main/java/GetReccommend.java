import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;

public class GetReccommend {

    public static void main(String[] args) throws IOException {


        HttpURLConnection con = Set.setConnection("http://localhost:9090/engines/pcrs_change/queries");
        String jsonInputString = toJsonStringUse(null,null,"cpu",null);
        System.out.println(jsonInputString);
        Set.doPost(con, jsonInputString);
        outJsonResponse(con);
    }

    public static String toJsonString(String value) {
        String jsonInputString = new String();

        jsonInputString = "{\n" +
                "    \"item\": \"amd-ryzen-5-3600\",\n" +
                "    \"rules\": [\n" +
                "    {\n" +
                "      \"name\": \"category\",\n" +
                "      \"values\": [\""+ value+ "\"],\n" +
                "      \"bias\": -1\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        return jsonInputString;
    }

    public static void outJsonResponse(HttpURLConnection con) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            Gson gson = new Gson();

            Result result = gson.fromJson(response.toString(), Result.class);
            List<ReccomendModel> reccomendModelList = result.getResult();

            for (ReccomendModel reccomendModel : reccomendModelList) {
                System.out.println(reccomendModel.getItem() + " " + reccomendModel.getScore());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toJsonStringUse(String type, String typeValue, String value, Integer id) {
        String jsonType = new String();
        String user = new String();
        String rules = "    \"rules\": [\n" +
                "    {\n" +
                "      \"name\": \"category\",\n" +
                "      \"values\": [\""+ value+ "\"],\n" +
                "      \"bias\": -1\n" +
                "    }\n" +
                "  ]\n" ;

        if(id!= null) {
            user = "    \"user\": \""+ id +"\",\n";
        }

        if(type!= null) {
            jsonType = "    \"" + type + "\": \""+ typeValue +"\",\n";
        }

        String jsonInputString = new String();
        jsonInputString = "{\n" +
                jsonType +
                user +
                rules +
                "}";

        return jsonInputString;
    }
}
