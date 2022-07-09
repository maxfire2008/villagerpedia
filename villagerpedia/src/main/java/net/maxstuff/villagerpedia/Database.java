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

    private static JSONObject getPlayerDB(UUID player) {
        JSONObject player_db;
        if (database_json.containsKey(player)) {
            player_db = (JSONObject) database_json.get(player);
        } else {
            player_db = new JSONObject();
        }
        return player_db;
    }

    private static void setPlayerDBItem(UUID player, Object key, Object value) {
        JSONObject player_db = getPlayerDB(player);
        player_db.put(key, value);
        database_json.put(player, player_db);
    }

    private static JSONArray getVillagers(UUID player) {
        JSONArray villagers;
        JSONObject player_db = getPlayerDB(player);

        if (database_json.containsKey("villagers")) {
            villagers = (JSONArray) player_db.get("villagers");
        } else {
            villagers = new JSONArray();
        }
        return villagers;
    }

    /* Other methods protected by singleton-ness */
    protected static void updateVillager(UUID player, JSONObject villager) {
        System.out.println(player);
        
        JSONArray villagers = getVillagers(player);

        villagers.add(villager);

        setPlayerDBItem(player, "villagers", villagers);
    }

    protected static Object getDefaultInterface(UUID player) {
        JSONObject player_db = getPlayerDB(player);
        if (player_db.containsKey("default_interface")) {
            return player_db.get("default_interface");
        } else {
            return null;
        }
    }

    protected static void setDefaultInterface(UUID player, Object default_interface) {
        setPlayerDBItem(player, "default_interface", default_interface);
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