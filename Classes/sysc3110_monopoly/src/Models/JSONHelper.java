package Models;

import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Author: Kareem El Assad;
 * Date: 2021-12-04;
 */

public class JSONHelper {
    static String path;
    static JSONArray tiles;

    // create constructor
    public JSONHelper(String language) throws IOException {
        if (language.equals("englishUK")) {
            path = "src/json/Board-english.json";
        } else if (language.equals("arabic")) {
            path = "src/json/Board-arabic.json";
        }
        readJsonToObject(path);
    }

    public static JSONObject readJsonToObject(String path) {

        JSONObject jsonObject = null;
        try {
            // Load File
            String content = new String((Files.readAllBytes(Paths.get(path))));
            jsonObject = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public JSONArray getTiles() {
        JSONObject obj = readJsonToObject(path);
        tiles = obj.getJSONArray("tiles");
        return tiles;
    }

    public String getCurrency() {
        JSONObject obj = readJsonToObject(path);
        return obj.getString("currency");
    }

    public static void main(String[] args) {
        try {
            JSONHelper jsonHelper = new JSONHelper("arabic");
            System.out.println("currency");
            System.out.println(jsonHelper.getCurrency());
            System.out.println("tiles");
            System.out.println(jsonHelper.getTiles());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}