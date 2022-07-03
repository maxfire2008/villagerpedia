package net.maxstuff.villagerpedia;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.google.gson.*;

import java.time.LocalDateTime;

public class Database {

    private static Database database = new Database();

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
            JsonElement jsonElement = JsonParser.parseString(string);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            System.out.println(jsonObject.keySet());
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
    protected static void updateVilager(String uuid) {
        System.out.println(uuid);
    }

    protected static void listVillagers() {
        System.out.println("Listing villagers");
    }

    protected static void save() {
        database.toString();
        System.out.println("Database save");
    }
}