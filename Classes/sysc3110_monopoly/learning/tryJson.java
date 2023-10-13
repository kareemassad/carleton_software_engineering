/*
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// Author : Kareem El Assad
// Date: 2021-11-07
// This class is used to read data from Models.Board-english.json file and print it to the console.
// Used as an example to introduce JSON

public class tryJson {
    public static void main(String[] args) {
        String path = "Models.Board-english.json";
        try {
            // Load File
            String content = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject jsonObject = new JSONObject(content);
            // Enter tiles item
            JSONArray tiles = jsonObject.getJSONArray("tiles");
            // To output all tiles:
            // System.out.println(tiles);
            // System.out.println(jsonObject.get("tiles"));

            // To select a specific tile:
            System.out.println("This prints tile 1");
            JSONObject go = (JSONObject) tiles.get(0);
            System.out.println(go);
            System.out.println(go.get("name"));
            String name = (String) go.get("name");
            // System.out.println(tiles.get(0));

            // Logic for printing and displaying the tiles
            // iterate thru all tiles
            for (int i = 0; i < tiles.length(); i++) {
                // get the ith tile
                JSONObject obj = (JSONObject) tiles.get(i);
                // get the type of the ith tile
                String type = (String) obj.get("type");
                // if
                if (type == "go") {
                    System.out.println(obj.get("name"));
                }
                if (type == "property") {
                    System.out.println(obj.get("name"));
                }

                String name1 = (String) obj.get("name");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

 */
