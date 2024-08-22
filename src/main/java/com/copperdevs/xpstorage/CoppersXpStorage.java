package com.copperdevs.xpstorage;

import com.copperdevs.xpstorage.config.ConfigData;
import com.copperdevs.xpstorage.config.ConfigManager;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoppersXpStorage implements ModInitializer {

    public static final String MOD_ID = "coppersxpstorage";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static ConfigData CONFIG = new ConfigData();

    private static final float bottlingConsumptionMinRange = 0f;
    private static final float bottlingConsumptionMaxRange = 1f;

    @Override
    public void onInitialize() {
        loadConfig();
        loadCommands();
    }

    private void loadConfig() {
        if (ConfigManager.ConfigFileExists()) CONFIG = ConfigManager.Load();
        else ConfigManager.Save(CONFIG);
    }

    private void loadCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("copperdevs").then(literal("xpstorage").then(literal("config").then(literal("reload").requires(source -> source.hasPermissionLevel(4)).executes(context -> {
            loadConfig();
            context.getSource().sendFeedback(() -> Text.literal("Reloading config"), true);

            return 1;
        }))))));
    }

    public static float getBottleConsumption() {
        if (CoppersXpStorage.CONFIG.bottlingConsumption >= CoppersXpStorage.bottlingConsumptionMaxRange) return CoppersXpStorage.bottlingConsumptionMaxRange;
        return Math.max(CoppersXpStorage.bottlingConsumptionMinRange, CoppersXpStorage.CONFIG.bottlingConsumption);
    }

    public static boolean getEnabledConfig() {
        return CoppersXpStorage.CONFIG.enabled;
    }

    public static boolean getSneakingRequiredConfig() {
        return CoppersXpStorage.CONFIG.sneakingRequired;
    }
}