package net.maxstuff.villagerpedia;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;
import java.util.UUID;

import java.time.LocalDateTime;

public class Database {
    private static Database database = new Database();
    private static JSONObject database_json;

    /*
     * A private Constructor prevents any other
     * class from instantiating.
     */
    private Database() {
        Path path = Path.of(System.getProperty("user.dir"), "plugins/Villagerpedia/database.json");
        if (!Files.exists(path)) {
            if (!Files.exists(path.getParent())) {
                try {
                    Files.createDirectories(path.getParent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Files.writeString(
                        path,
                        "{}",
                        StandardOpenOption.CREATE_NEW);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            String string = Files.readString(path);
            JSONParser parser = new JSONParser();
            try {
                database_json = (JSONObject) parser.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // System.out.println(jsonObject.keySet());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(database.isSet("villagers"));

        // if (database.isSet("villagers")) {
        // System.out.println(database.get("villagers"));
        // } else {
        // database.set("villagers", LocalDateTime.now().toString());
        // }
        System.out.println("Database created");
    }

    /* Static 'instance' method */
    public static Database getInstance() {
        return database;
    }

    /* Other methods protected by singleton-ness */
    protected static void updateVillager(UUID player, JSONObject villager) {
        System.out.println(player);
        JSONObject player_db;
        if (database_json.containsKey(player)) {
            player_db = (JSONObject) database_json.get(player);
        } else {
            player_db = new JSONObject();
        }
        player_db.put(villager.get("uuid"),villager);
        database_json.put(player, player_db);
    }

    protected static void listVillagers(UUID player) {
        System.out.println("Listing villagers");
        JSONObject player_db = (JSONObject) database_json.get(player);
        for (Map.Entry<String, JSONObject> villager : player_db.entrySet()) {
            System.out.println(villager.getValue().get("name"));
        }
    }

    protected static void save() {
        Path path = Path.of(System.getProperty("user.dir"), "plugins/Villagerpedia/database.json");
        //write database.toString() to path
        try {
            Files.writeString(path, database_json.toJSONString(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Database save");
    }
}