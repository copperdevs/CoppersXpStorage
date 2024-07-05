package com.copperdevs.mods.coppersxpstorage.config;

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

        try (FileReader reader = new FileReader(PATH)) {
            return gson.fromJson(reader, ConfigData.class);
        } catch (IOException ignored) {
        }

        return new ConfigData();
    }

    public static void Save(ConfigData data) {
        try (FileWriter writer = new FileWriter(PATH)) {
            writer.write(gson.toJson(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
