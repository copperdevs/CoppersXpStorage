package com.copperdevs.xpstorage.config;

import com.copperdevs.xpstorage.CoppersXpStorage;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    private static final Gson gson = new Gson();
    private static final String PATH = "config/copper-xpstorage-config.json";

    public static boolean ConfigFileExists() {
        return new File(PATH).exists();
    }

    public static ConfigData Load() {
        var configFile = new File(PATH);

        if (configFile.exists() && configFile.canRead()) {
            try (FileReader reader = new FileReader(PATH)) {
                return gson.fromJson(reader, ConfigData.class);
            } catch (IOException e) {
                CoppersXpStorage.LOGGER.error(e.getMessage());
            }
        }

        var newConfig = new ConfigData();
        Save(newConfig);
        return newConfig;
    }

    public static void Save(ConfigData data) {
        try (FileWriter writer = new FileWriter(PATH)) {
            writer.write(gson.toJson(data));
        } catch (IOException e) {
            CoppersXpStorage.LOGGER.error(e.getMessage());
        }
    }
}
