package com.copperdevs.xpstorage.coppersxpstorage;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CoppersXpStorage implements ModInitializer {

    public static final String MOD_ID = "coppersxpstorage";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final float BottleXpConsumption = 0.85f;

    @Override
    public void onInitialize() {
    }

    public static Identifier id(String id) {
        return Identifier.of(MOD_ID);
    }
}