package com.copperdevs.xpstorage.coppersxpstorage;

import com.copperdevs.xpstorage.coppersxpstorage.config.ConfigData;
import com.copperdevs.xpstorage.coppersxpstorage.config.ConfigManager;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CoppersXpStorage implements ModInitializer {

    public static final String MOD_ID = "coppersxpstorage";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ConfigData CONFIG = new ConfigData();

    @Override
    public void onInitialize() {
        if (ConfigManager.ConfigFileExists())
            CONFIG = ConfigManager.Load();
        else
            ConfigManager.Save(CONFIG);
    }

    public static Identifier id(String id) {
        return Identifier.of(MOD_ID);
    }
}